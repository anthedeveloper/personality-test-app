package com.an.test.personalitytestapp.service;

import com.an.test.personalitytestapp.model.Answer;
import com.an.test.personalitytestapp.repository.AnswerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


class AnswerServiceTest {

    private AnswerService underTest;

    @Mock
    private AnswerRepository answerRepository;
    AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new AnswerService(answerRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void saveAnswer_notExistingAnswer() {
        given(answerRepository.isQuestionAnsweredByUser(1,1)).willReturn(false);

        Answer answer = new Answer(1,1,"Answer1");
        underTest.saveAnswer(answer);

        ArgumentCaptor<Answer> answerArgumentCaptor = ArgumentCaptor.forClass(Answer.class);

        verify(answerRepository).save(answerArgumentCaptor.capture());

        assertThat(answerArgumentCaptor.getValue()).isEqualTo(answer);
    }

    @Test
    void saveAnswer_existingAnswer(){
        given(answerRepository.isQuestionAnsweredByUser(1,1)).willReturn(true);

        Answer answer = new Answer(1,1,"Answer1");
        underTest.saveAnswer(answer);

        verify(answerRepository).deleteAlreadyAnsweredByQuestionIdUseAndUserId(1,1);
    }
}