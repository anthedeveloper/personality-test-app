package com.an.test.personalitytestapp.repository;

import com.an.test.personalitytestapp.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void test_isUserEmailExits_returnsTrue(){
        User givenUser = new User("ayse", "ayse@gmail.com");
        underTest.save(givenUser);

        boolean actual = underTest.isUserEmailExits("ayse@gmail.com");

        assertTrue(actual);
    }

    @Test
    void test_isUserEmailExits_returnsFalse(){
        boolean actual = underTest.isUserEmailExits("ayse@gmail.com");

        assertFalse(actual);
    }
}