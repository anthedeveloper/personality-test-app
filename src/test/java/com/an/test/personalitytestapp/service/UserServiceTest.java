package com.an.test.personalitytestapp.service;

import com.an.test.personalitytestapp.exception.UserEmailAlreadyExistsException;
import com.an.test.personalitytestapp.model.User;
import com.an.test.personalitytestapp.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

class UserServiceTest {

    private UserService underTest;

    @Mock
    private UserRepository userRepository;

    AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new UserService(userRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void test_getAllUsers() {
        underTest.getAllUsers();
        Mockito.verify(userRepository).findAll();
    }

    @Test
    void addUser() {
        User user = new User("ayse","ayse@gmail.com");
        underTest.addUser(user);

        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);

        verify(userRepository).save(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();

        assertThat(capturedUser).isEqualTo(user);
    }

    @Test
    void addUser_exceptionWillThrow(){
        User user = new User("ayse","ayse@gmail.com");

        given(userRepository.isUserEmailExits("ayse@gmail.com")).willReturn(true);

        assertThatThrownBy(() -> underTest.addUser(user))
                        .isInstanceOf(UserEmailAlreadyExistsException.class)
                        .hasMessageContaining("Email " + user.getEmail() + " already taken!");

        verify(userRepository, never()).save(user);
    }
}