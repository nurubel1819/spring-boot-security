package com.example.Basic.Authentication.controller;

import com.example.Basic.Authentication.repo.UserDetailsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/security")
@RequiredArgsConstructor
public class SecurityController {

    private final UserDetailsRepo userDetailsRepo;

    @GetMapping("/test")
    private String securityTest()
    {
        return "hello";
    }
}
