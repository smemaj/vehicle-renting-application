package com.spring.vehiclerenting.errors.handler;

import com.spring.vehiclerenting.dto.response.MessageResponse;
import com.spring.vehiclerenting.errors.exception.CannotUpdateStatusException;
import com.spring.vehiclerenting.errors.exception.RoleNotFoundException;
import com.spring.vehiclerenting.errors.exception.UserDoesNotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {


    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(UserDoesNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public MessageResponse userDoesNotExistException(){
        logger.error("User Does Not Exist");
        return new MessageResponse("User Does Not Exist");
    }

    @ExceptionHandler(CannotUpdateStatusException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public MessageResponse cannotUpdateStatusException(){
        logger.error("Cannot update status");
        return new MessageResponse("Cannot update status");
    }

    @ExceptionHandler(RoleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public MessageResponse roleNotFoundException(){
        logger.error("Role not found");
        return new MessageResponse("Role not found");
    }
}
