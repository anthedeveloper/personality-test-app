package com.an.test.personalitytestapp.repository;

import com.an.test.personalitytestapp.model.Answer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AnswerRepositoryTest {
    @Autowired
    private AnswerRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void isQuestionAnsweredByUser_returnsTrue() {
        Answer answer = new Answer(1, 1, "ALWAYS");
        underTest.save(answer);

        boolean actual = underTest.isQuestionAnsweredByUser(1,1);

        assertTrue(actual);
    }

    @Test
    void isQuestionAnsweredByUser_returnsFalse() {
        boolean actual = underTest.isQuestionAnsweredByUser(1,1);

        assertFalse(actual);
    }

    @Test
    void deleteAlreadyAnsweredByQuestionIdUseAndUserId() {
        Answer answer = new Answer(1, 1, "ALWAYS");
        underTest.save(answer);

        boolean exists = underTest.isQuestionAnsweredByUser(1,1);

        assertTrue(exists);
        underTest.deleteAlreadyAnsweredByQuestionIdUseAndUserId(1,1);

        boolean notexists = underTest.isQuestionAnsweredByUser(1,1);

        assertFalse(notexists);
    }
}