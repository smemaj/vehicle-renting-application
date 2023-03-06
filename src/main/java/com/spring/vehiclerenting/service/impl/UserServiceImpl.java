package com.spring.vehiclerenting.service.impl;

import com.spring.vehiclerenting.errors.exception.RoleNotFoundException;
import com.spring.vehiclerenting.model.*;
import com.spring.vehiclerenting.repository.ApplicationRepository;
import com.spring.vehiclerenting.repository.RoleRepository;
import com.spring.vehiclerenting.repository.UserRepository;
import com.spring.vehiclerenting.repository.VehicleRepository;
import com.spring.vehiclerenting.service.ApplicationService;
import com.spring.vehiclerenting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private BCryptPasswordEncoder encoder;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public void updatePassword(String username, String oldPassword, String newPassword) {
        User user = this.userRepository.findByUsername(username);
        System.out.println("given username "+username);
        System.out.println("old password "+oldPassword);
        System.out.println("new password "+newPassword);
        System.out.println("existing username "+user.getUsername());
        System.out.println("existing password "+user.getPassword());
        if(this.encoder.matches(oldPassword, user.getPassword())){
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
        Set<Application> apps = user.getApplications();

        Vehicle vehicle;

        for(Application app:apps){
            vehicle=app.getVehicle();
            vehicle.getApplications().remove(app);
            this.vehicleRepository.save(vehicle);
        }

        this.applicationRepository.deleteAll(apps);
        this.userRepository.delete(user);
    }

    @Override
    public void createUser(String username, String password, String email, String phone, Set<String> roles) {
        User user = new User(username,
                password,
                email);

        Set<Role> newRoles = new HashSet<>();

        if (roles == null) {
            Role userRole = roleRepository.findByName(UserRoles.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            newRoles.add(userRole);
        } else {
            roles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = null;
                        try {
                            adminRole = roleRepository.findByName(UserRoles.ROLE_ADMIN)
                                    .orElseThrow(() -> new RoleNotFoundException(UserRoles.ROLE_ADMIN));
                        } catch (RoleNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        newRoles.add(adminRole);

                        break;
                    default:
                        Role userRole = null;
                        try {
                            userRole = roleRepository.findByName(UserRoles.ROLE_USER)
                                    .orElseThrow(() -> new RoleNotFoundException(UserRoles.ROLE_USER));
                        } catch (RoleNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        newRoles.add(userRole);
                }
            });
        }

        user.setRoles(newRoles);
        userRepository.save(user);

        this.userRepository.save(user);
    }
}











