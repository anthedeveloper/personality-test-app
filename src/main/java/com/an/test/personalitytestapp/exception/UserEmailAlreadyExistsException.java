package com.an.test.personalitytestapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserEmailAlreadyExistsException extends RuntimeException{

    public UserEmailAlreadyExistsException(String message) {
        super(message);
    }
}
