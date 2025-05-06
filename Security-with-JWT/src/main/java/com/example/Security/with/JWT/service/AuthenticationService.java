package com.example.Security.with.JWT.service;

import com.example.Security.with.JWT.dto.JwtAuthenticationResponseDto;
import com.example.Security.with.JWT.dto.RefreshTokenRequestDto;
import com.example.Security.with.JWT.dto.SignInRequestDto;
import com.example.Security.with.JWT.dto.SignUpRequestDto;
import com.example.Security.with.JWT.entity.Role;
import com.example.Security.with.JWT.entity.User;
import com.example.Security.with.JWT.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public User signup(SignUpRequestDto signUpRequestDto){
        User user = new User();
        user.setEmail(signUpRequestDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequestDto.getPassword()));
        user.setUserName(signUpRequestDto.getUserName());
        user.setRole(Role.USER);
        return userRepository.save(user);
    }

    public JwtAuthenticationResponseDto signIn(SignInRequestDto signInRequestDto){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInRequestDto.getEmail(),signInRequestDto.getPassword()
        ));
        var user = userRepository.findByEmail(signInRequestDto.getEmail()).orElseThrow(()-> new IllegalArgumentException("Invalid username or password"));
        var token = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(),user);

        System.out.println("token : "+token);
        System.out.println("Refresh token = "+refreshToken);

        JwtAuthenticationResponseDto response = new JwtAuthenticationResponseDto();
        response.setToken(token);
        response.setRefreshToken(refreshToken);
        return response;

    }
    public JwtAuthenticationResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequestDto){
        String userEmail = jwtService.extractUsername(refreshTokenRequestDto.getRefreshToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow(()-> new IllegalArgumentException("Invalid username or password"));

        if(jwtService.isTokenValid(refreshTokenRequestDto.getRefreshToken(),user)){
             var token = jwtService.generateToken(user);

             JwtAuthenticationResponseDto response = new JwtAuthenticationResponseDto();
             response.setToken(token);
             response.setRefreshToken(refreshTokenRequestDto.getRefreshToken());
             return response;
        }
        return null;
    }
}
