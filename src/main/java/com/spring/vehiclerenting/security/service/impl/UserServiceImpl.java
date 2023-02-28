package com.spring.vehiclerenting.security.service.impl;

import com.spring.vehiclerenting.model.User;
import com.spring.vehiclerenting.repository.RoleRepository;
import com.spring.vehiclerenting.repository.UserRepository;
import com.spring.vehiclerenting.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
}
