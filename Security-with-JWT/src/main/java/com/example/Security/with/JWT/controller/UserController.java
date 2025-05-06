package com.example.Security.with.JWT.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class UserController {
    @GetMapping()
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello User");
    }
}
