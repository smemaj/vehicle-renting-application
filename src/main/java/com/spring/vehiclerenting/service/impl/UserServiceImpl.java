package com.spring.vehiclerenting.service.impl;

import com.spring.vehiclerenting.model.Role;
import com.spring.vehiclerenting.model.User;
import com.spring.vehiclerenting.repository.RoleRepository;
import com.spring.vehiclerenting.repository.UserRepository;
import com.spring.vehiclerenting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private PasswordEncoder encoder;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Override
    public void deleteUser(Long userId) {

    }

    @Override
    public void updatePassword(String username, String oldPassword, String newPassword) {
        User user = this.userRepository.findByUsername(username);
        System.out.println("given username "+username);
        System.out.println("old password "+oldPassword);
        System.out.println("new password "+newPassword);
        System.out.println("existing username "+user.getUsername());
        System.out.println("existing password "+user.getPassword());
        System.out.println("old password "+encoder.encode(oldPassword));
        if(encoder.matches(user.getPassword(), oldPassword)){
            System.out.println("true");
            user.setPassword(encoder.encode(newPassword));
            System.out.println("updated password: "+user.getPassword());
            this.userRepository.save(user);
        }else {
            System.out.println("false");
        }
    }

    @Override
    public void updateUser(String username, String newUsername, String newEmail, String newPhone) {
        User user = this.userRepository.findByUsername(username);
        user.setUsername(newUsername);
        user.setEmail(newEmail);
        user.setPhone(newPhone);
        this.userRepository.save(user);
    }

    @Override
    public void deleteUser(String username) {
        User user = this.userRepository.findByUsername(username);
        this.userRepository.delete(user);
    }

    @Override
    public void createUser(String username, String password, String email, String phone, Set<Role> roles) {
        User user = new User(username, password, email, phone, roles);
        this.userRepository.save(user);
    }
}