package com.spring.vehiclerenting.service;

import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface UserService {

    void updatePassword(String username, String oldPassword, String newPassword);
    void updateUser(String username, String newUsername, String newEmail, String newPhone);

    void deleteUser(String username);

    void createUser(String username, String password, String email, String phone, Set<String> roles);
}

