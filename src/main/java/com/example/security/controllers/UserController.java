package com.example.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.security.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("get-all-users")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getAllUsers(@AuthenticationPrincipal UserDetails user) {
        System.out.println("Authenticated user: " + user.getUsername());
        return ResponseEntity.ok(userRepository.findAll());
    }
    
}
