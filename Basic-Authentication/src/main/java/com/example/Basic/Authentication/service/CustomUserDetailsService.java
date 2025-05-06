package com.example.Basic.Authentication.service;

import com.example.Basic.Authentication.repo.UserDetailsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired // if here i user "final" and @RequiredArgsConstructor, then one error find in securityConfiguration class method userDetailsService() return type
    private UserDetailsRepo userDetailsRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDetailsRepo.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("User not found"));
    }
}
