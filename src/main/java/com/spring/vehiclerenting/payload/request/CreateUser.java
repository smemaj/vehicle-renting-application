package com.spring.vehiclerenting.payload.request;

import com.spring.vehiclerenting.model.Role;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class CreateUser {

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String email;

    private String phone;

    @NotNull
    private Set<Role> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
