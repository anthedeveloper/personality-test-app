package com.an.test.personalitytestapp.service;

import com.an.test.personalitytestapp.model.Question;
import com.an.test.personalitytestapp.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> getAllQuestions(){
        return questionRepository.findAll();
    }

    public Question getQuestionById(long questionId) {
        return questionRepository.findById(questionId).get();
    }

}
