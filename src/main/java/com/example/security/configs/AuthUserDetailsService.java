package com.example.security.configs;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.example.security.repository.UserRepository;
import com.example.security.models.User;

@Service
public class AuthUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User appUser = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return org.springframework.security.core.userdetails.User.builder()
                .username(appUser.getUsername())
                .password(appUser.getPassword())
                .roles(appUser.getRole() != null ? appUser.getRole() : "USER")
                .build();
    }
}


