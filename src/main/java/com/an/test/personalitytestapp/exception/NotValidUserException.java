package com.an.test.personalitytestapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NotValidUserException extends RuntimeException{

    public NotValidUserException(String message) {
        super(message);
    }
}
