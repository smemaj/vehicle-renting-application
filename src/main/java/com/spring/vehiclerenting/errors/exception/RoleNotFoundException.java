package com.spring.vehiclerenting.errors.exception;

import com.spring.vehiclerenting.model.UserRoles;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Cannot find role") //404
public class RoleNotFoundException extends Exception{
    private static final long serialVersionUID = -3332292346834265371L;

    public RoleNotFoundException(UserRoles name) {
        super("RoleNotFoundException with name: " + name);
    }
}
