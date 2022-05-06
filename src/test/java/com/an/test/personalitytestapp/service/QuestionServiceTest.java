package com.an.test.personalitytestapp.service;

import com.an.test.personalitytestapp.model.Question;
import com.an.test.personalitytestapp.repository.QuestionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class QuestionServiceTest {

    private QuestionService underTest;

    @Mock
    private QuestionRepository questionRepository;

    AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new QuestionService(questionRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void getAllQuestions() {
        underTest.getAllQuestions();
        verify(questionRepository).findAll();
    }

    @Test
    void getQuestionById() {
        //to handle empty result of findById for get()
        given(questionRepository.findById(Mockito.anyLong())).willReturn(Optional.of(new Question()));

        underTest.getQuestionById(1);

        verify(questionRepository).findById(Mockito.anyLong());
    }
}