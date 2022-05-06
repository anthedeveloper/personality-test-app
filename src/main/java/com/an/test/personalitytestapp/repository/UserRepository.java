package com.an.test.personalitytestapp.repository;

import com.an.test.personalitytestapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository
        extends JpaRepository<User, Long> {

    @Query( value =
            "SELECT CASE WHEN COUNT(1) > 0      " +
            "               THEN TRUE           " +
            "            ELSE FALSE END         " +
            "   FROM users U WHERE U.EMAIL = ?1 " ,
            nativeQuery = true
    )
    Boolean isUserEmailExits(String email);
}
