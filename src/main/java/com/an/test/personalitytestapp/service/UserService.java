package com.an.test.personalitytestapp.service;

import com.an.test.personalitytestapp.exception.NotValidUserException;
import com.an.test.personalitytestapp.exception.UserEmailAlreadyExistsException;
import com.an.test.personalitytestapp.model.User;
import com.an.test.personalitytestapp.repository.UserRepository;
import com.an.test.personalitytestapp.tools.Util;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void addUser(User user) {
        if(Util.isEmpty(user.getEmail())){
            throw new NotValidUserException("Please enter a valid email!");
        }
        Boolean isExistsEmail = userRepository.isUserEmailExits(user.getEmail());
        if(isExistsEmail){
            throw new UserEmailAlreadyExistsException("Email " + user.getEmail() + " already taken!");
        }
        userRepository.save(user);
    }
}
