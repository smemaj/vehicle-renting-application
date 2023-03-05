package com.spring.vehiclerenting.controller;


import com.spring.vehiclerenting.dto.request.*;
import com.spring.vehiclerenting.dto.response.MessageResponse;
import com.spring.vehiclerenting.errors.exception.UserDoesNotExistException;
import com.spring.vehiclerenting.repository.RoleRepository;
import com.spring.vehiclerenting.repository.UserRepository;
import com.spring.vehiclerenting.service.ApplicationService;
import com.spring.vehiclerenting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/userRole")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }

    @PutMapping("/updatePassword")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> updatePassword(@Valid @RequestBody PasswordUpdate passwordUpdate) throws UserDoesNotExistException {
        if (!userRepository.existsByUsername(passwordUpdate.getUsername())) {
            throw new UserDoesNotExistException(passwordUpdate.getUsername());
        }
        this.userService.updatePassword(passwordUpdate.getUsername(), passwordUpdate.getOldPassword(), passwordUpdate.getNewPassword());
        return ResponseEntity.ok(new MessageResponse("User password updated successfully!"));
    }

    @PutMapping("/updateUser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserUpdate userUpdate) throws UserDoesNotExistException {
        if (!userRepository.existsByUsername(userUpdate.getUsername())) {
            throw new UserDoesNotExistException(userUpdate.getUsername());
        }
        if (userRepository.existsByEmail(userUpdate.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: User with this email already exists!"));
        }

        if (userRepository.existsByUsername(userUpdate.getNewUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: User with this username already exists!"));
        }
        this.userService.updateUser(userUpdate.getUsername(), userUpdate.getNewUsername(), userUpdate.getEmail(), userUpdate.getPhone());
        return ResponseEntity.ok(new MessageResponse("User updated successfully!"));
    }

    @DeleteMapping("/deleteUser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@Valid @RequestBody DeleteUser deleteUser) throws UserDoesNotExistException {
        if (!userRepository.existsByUsername(deleteUser.getUsername())) {
            throw new UserDoesNotExistException(deleteUser.getUsername());
        }
        this.userService.deleteUser(deleteUser.getUsername());
        return ResponseEntity.ok(new MessageResponse("User deleted successfully!"));
    }

    @PostMapping("/createUser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUser createUser){
        if (userRepository.existsByUsername(createUser.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: User with this username already exists!"));
        }
        if (userRepository.existsByEmail(createUser.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: User with this email already exists!"));
        }
        this.userService.createUser(createUser.getUsername(), encoder.encode(createUser.getPassword()), createUser.getEmail(), createUser.getPhone(), createUser.getRoles());
        return ResponseEntity.ok(new MessageResponse("User added successfully!"));
    }

}