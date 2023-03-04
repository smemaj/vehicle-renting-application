package com.spring.vehiclerenting.service;

import com.spring.vehiclerenting.model.Role;
import com.spring.vehiclerenting.model.User;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface UserService {


    //create user
//    public User createUser(User user, UserRole userRole) throws Exception;
//
//    //get user
//    public User getUser(String username);

    //delete user
    void deleteUser(Long userId);

    void updatePassword(String username, String oldPassword, String newPassword);
    void updateUser(String username, String newUsername, String newEmail, String newPhone);

    void deleteUser(String username);

    void createUser(String username, String password, String email, String phone, Set<Role> roles);
}

