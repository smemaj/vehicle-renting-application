package com.spring.vehiclerenting.errors.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Cannot update status") //404
public class CannotUpdateStatusException extends Exception{
    private static final long serialVersionUID = -3332292346834265371L;

    public CannotUpdateStatusException(Long id) {
        super("CannotUpdateStatusException with id: " + id);
    }
}
