package com.example.Security.with.JWT.dto;

import lombok.Data;

@Data
public class SignInRequestDto {
    private String email;
    private String password;
}
