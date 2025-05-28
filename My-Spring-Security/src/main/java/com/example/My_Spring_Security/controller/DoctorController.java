package com.example.My_Spring_Security.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @GetMapping("hello-doctor")
    private ResponseEntity<String> helloDoctor() {
        return ResponseEntity.ok("Hello i am Doctor");
    }
}
