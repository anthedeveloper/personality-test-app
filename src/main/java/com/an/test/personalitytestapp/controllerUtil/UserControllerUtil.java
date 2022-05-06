package com.an.test.personalitytestapp.controllerUtil;

import com.an.test.personalitytestapp.exception.NotFoundException;
import com.an.test.personalitytestapp.exception.NotValidUserException;
import com.an.test.personalitytestapp.exception.UserEmailAlreadyExistsException;
import com.an.test.personalitytestapp.model.Question;
import com.an.test.personalitytestapp.model.User;
import com.an.test.personalitytestapp.service.QuestionService;
import com.an.test.personalitytestapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class UserControllerUtil {

    private final UserService userService;
    private final QuestionService questionService;

    public ResponseEntity<Question> addUser(User user) {
        try {
            userService.addUser(user);
            return new ResponseEntity<>(questionService.getQuestionById(1), HttpStatus.OK);
        } catch (UserEmailAlreadyExistsException e1) {
            throw e1;
        } catch (Exception e) {
            throw new NotValidUserException("Please check the information you provided!");
        }
    }

    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        if (allUsers.isEmpty()) {
            throw new NotFoundException("No one took the Personality Test yet!");
        }
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }
}
