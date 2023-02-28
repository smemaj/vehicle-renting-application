package com.spring.vehiclerenting.payload.request;

import javax.validation.constraints.NotNull;

public class DeleteUser {

    @NotNull
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
