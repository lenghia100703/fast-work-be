package com.fastwork.controllers;

import com.fastwork.dtos.auth.AuthResponseDto;
import com.fastwork.dtos.auth.LoginDto;
import com.fastwork.dtos.auth.SignUpDto;
import com.fastwork.dtos.common.CommonResponseDto;
import com.fastwork.dtos.user.UserDto;
import jakarta.mail.MessagingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/auth")
public interface AuthController {
    @PostMapping("/register")
    CommonResponseDto<UserDto> register(@RequestBody SignUpDto signUpDto) throws MessagingException;

    @PostMapping("/login")
    CommonResponseDto<AuthResponseDto> login(@RequestBody LoginDto loginDto);

    @PostMapping("/logout")
    CommonResponseDto<String> logout();
}
