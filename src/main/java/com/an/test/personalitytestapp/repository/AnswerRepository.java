package com.an.test.personalitytestapp.repository;

import com.an.test.personalitytestapp.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AnswerRepository
        extends JpaRepository<Answer, Long> {

    @Transactional
    @Query( value =
            "SELECT CASE WHEN COUNT(1) > 0      " +
            "               THEN TRUE           " +
            "            ELSE FALSE END         " +
            "   FROM answers A                  " +
            "   WHERE A.QUESTION_ID = ?1        " +
            "         AND A.USER_ID = ?2        " ,
            nativeQuery = true
    )
    Boolean isQuestionAnsweredByUser(long questionId, long userId);

    @Transactional
    @Modifying
    @Query(value = " DELETE FROM ANSWERS A WHERE A.QUESTION_ID = ?1 AND A.USER_ID = ?2",
            nativeQuery = true)
    void deleteAlreadyAnsweredByQuestionIdUseAndUserId(long questionId, long userId);
}
