package com.example.My_Spring_Security.dto;

import lombok.Data;

@Data
public class SignInRequestDto {
    private String phone;
    private String password;
}
