package com.an.test.personalitytestapp.service;

import com.an.test.personalitytestapp.model.Answer;
import com.an.test.personalitytestapp.repository.AnswerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    public void saveAnswer(Answer answer) {
        try{
            if(answerRepository.isQuestionAnsweredByUser(answer.getQuestionId(), answer.getUserId())){
                answerRepository.deleteAlreadyAnsweredByQuestionIdUseAndUserId(answer.getQuestionId(), answer.getUserId());
            }
            answerRepository.save(answer);
        }
        catch (Exception e){
            System.out.println(e);
        }

    }
}
