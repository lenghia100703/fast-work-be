package com.fastwork.dtos.user;

import lombok.Data;

@Data
public class ChangePasswordDto {
    private String currentPassword;
    private String password;
    private String confirmPassword;
}
