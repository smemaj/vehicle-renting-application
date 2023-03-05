package com.spring.vehiclerenting.errors.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="User Does Not Exist") //404
public class UserDoesNotExistException extends Exception {

    private static final long serialVersionUID = -3332292346834265371L;

    public UserDoesNotExistException(String username) {
        super("UserDoesNotExistException with username: " + username);
    }

}