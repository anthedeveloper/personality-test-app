package com.an.test.personalitytestapp.controller;

import com.an.test.personalitytestapp.controllerUtil.UserControllerUtil;
import com.an.test.personalitytestapp.model.Question;
import com.an.test.personalitytestapp.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private final UserControllerUtil controllerUtil;

    /****
     * This method will start the test. Therefore, returns the first question
     * @param user
     * @return the first question
     */
    @PostMapping(path = "/addTestTaker")
    public ResponseEntity<Question>  addUser(@Valid @RequestBody User user){
        return controllerUtil.addUser(user);
    }

    /***
     * I might use this method for report
     * @return
     */
    @GetMapping(path = "/getTestTakers")
    public ResponseEntity<List<User>> getAllUsers(){
        return controllerUtil.getAllUsers();
    }
}
