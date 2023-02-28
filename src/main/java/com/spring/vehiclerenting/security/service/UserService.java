package com.spring.vehiclerenting.security.service;

import com.spring.vehiclerenting.model.User;
import org.springframework.stereotype.Service;


public interface UserService {


    //create user
//    public User createUser(User user, UserRole userRole) throws Exception;
//
//    //get user
//    public User getUser(String username);

    //delete user
    public void deleteUser(Long userId);

    public void updatePassword(String username, String oldPassword, String newPassword);
}

