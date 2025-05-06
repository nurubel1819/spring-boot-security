package com.example.Security.with.JWT.controller;

import com.example.Security.with.JWT.dto.JwtAuthenticationResponseDto;
import com.example.Security.with.JWT.dto.RefreshTokenRequestDto;
import com.example.Security.with.JWT.dto.SignInRequestDto;
import com.example.Security.with.JWT.dto.SignUpRequestDto;
import com.example.Security.with.JWT.entity.User;
import com.example.Security.with.JWT.service.AuthenticationService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
//@SecurityRequirement(name = "bearerAuth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignUpRequestDto signUpRequestDto){
        return ResponseEntity.ok(authenticationService.signup(signUpRequestDto));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponseDto> signIn(@RequestBody SignInRequestDto signInRequestDto){

        return ResponseEntity.ok(authenticationService.signIn(signInRequestDto));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponseDto> refresh(@RequestBody RefreshTokenRequestDto refreshTokenRequestDto){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequestDto));
    }
}
