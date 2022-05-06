package com.an.test.personalitytestapp.repository;

import com.an.test.personalitytestapp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository
        extends JpaRepository<Question, Long> {
}
