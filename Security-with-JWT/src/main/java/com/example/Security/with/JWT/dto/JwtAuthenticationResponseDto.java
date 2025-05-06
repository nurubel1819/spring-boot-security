package com.example.Security.with.JWT.dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponseDto {
    private String token;
    private String refreshToken;
}
