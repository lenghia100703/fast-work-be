package com.fastwork.dtos.auth;

import lombok.Data;

@Data
public class SignUpDto {
    private String username;

    private String email;

    private String password;

    private String confirmPassword;

    private String role;
}
