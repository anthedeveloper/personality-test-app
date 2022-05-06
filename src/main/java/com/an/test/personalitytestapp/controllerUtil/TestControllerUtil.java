package com.an.test.personalitytestapp.controllerUtil;

import com.an.test.personalitytestapp.exception.EmptyAnswerException;
import com.an.test.personalitytestapp.exception.NotFoundException;
import com.an.test.personalitytestapp.model.Answer;
import com.an.test.personalitytestapp.model.Question;
import com.an.test.personalitytestapp.service.AnswerService;
import com.an.test.personalitytestapp.service.QuestionService;
import com.an.test.personalitytestapp.tools.Util;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@Component
public class TestControllerUtil {
    private final QuestionService questionService;
    private final AnswerService answerService;

    /***
     * This method returns all the questions.
     * Not needed but I added to check whether the questions are loaded properly.
     * @return
     */
    public ResponseEntity<List<Question>> getQuestionList(){
        List<Question> questions = questionService.getAllQuestions();
        if(questions.isEmpty()){
            throw new NotFoundException("Questions are not loaded! Please try again later.");
        }
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    /***
     * This method saves the last answer.
     * This path will be called via Submit button.
     * @param answer
     * @return
     */
    public ResponseEntity<String> submitTest(@Valid @RequestBody Answer answer){
        isAnswerValid(answer);
        answerService.saveAnswer(answer);
        return new ResponseEntity<>("Thank you for taking the test!", HttpStatus.OK);
    }

    /***
     * This method will be used to check whether the answer provieded,
     * save the answer
     * then return the next question.
     * Single Choice Conditional question is handled in this method.
     * @param answer
     * @return
     */
    public ResponseEntity<Question> nextQuestion(@RequestBody Answer answer){
        isAnswerValid(answer);
        answerService.saveAnswer(answer);
        long nextQuestionId = answer.getQuestionId() + 1;
        if(answer.getQuestionId() == 3
                && !"VERY IMPORTANT".equalsIgnoreCase(answer.getAnswer())){
            nextQuestionId = answer.getQuestionId() + 2;  //overwrite if the conditional question answer as condition
        }
        Question nextQuestion = questionService.getQuestionById(nextQuestionId);
        return new ResponseEntity<Question>(nextQuestion, HttpStatus.OK);
    }

    private void isAnswerValid(@RequestBody Answer answer) {
        if (Util.isEmpty(answer.getAnswer())) {
            throw new EmptyAnswerException("Need Answer for the question!");
        }
    }
}
