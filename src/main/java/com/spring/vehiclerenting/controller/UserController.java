package com.spring.vehiclerenting.controller;


import com.spring.vehiclerenting.payload.request.DeleteUser;
import com.spring.vehiclerenting.payload.request.Login;
import com.spring.vehiclerenting.payload.request.PasswordUpdate;
import com.spring.vehiclerenting.payload.request.UserUpdate;
import com.spring.vehiclerenting.payload.response.MessageResponse;
import com.spring.vehiclerenting.repository.RoleRepository;
import com.spring.vehiclerenting.repository.UserRepository;
import com.spring.vehiclerenting.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

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
    public ResponseEntity<?> updatePassword(@Valid @RequestBody PasswordUpdate passwordUpdate){
        if (!userRepository.existsByUsername(passwordUpdate.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: User with this username does not exist!"));
        }
        this.userService.updatePassword(passwordUpdate.getUsername(), passwordUpdate.getOldPassword(), passwordUpdate.getNewPassword());
        return ResponseEntity.ok(new MessageResponse("User password updated successfully!"));
    }

    @PutMapping("/updateUser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserUpdate userUpdate){
        if (!userRepository.existsByUsername(userUpdate.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: User with this username does not exist!"));
        }
        this.userService.updateUser(userUpdate.getUsername(), userUpdate.getNewUsername(), userUpdate.getEmail(), userUpdate.getPhone());
        return ResponseEntity.ok(new MessageResponse("User updated successfully!"));
    }

    @DeleteMapping("/deleteUser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@Valid @RequestBody DeleteUser deleteUser){
        if (!userRepository.existsByUsername(deleteUser.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: User with this username does not exist!"));
        }
        this.userService.deleteUser(deleteUser.getUsername());
        return ResponseEntity.ok(new MessageResponse("User deleted successfully!"));
    }

}