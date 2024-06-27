package com.fastwork.services;


import com.fastwork.dtos.auth.AuthResponseDto;
import com.fastwork.dtos.auth.EmailDto;
import com.fastwork.dtos.auth.LoginDto;
import com.fastwork.dtos.auth.SignUpDto;
import com.fastwork.dtos.common.CommonResponseDto;
import com.fastwork.dtos.user.UserDto;
import jakarta.mail.MessagingException;

public interface AuthService {
    CommonResponseDto<AuthResponseDto> login(LoginDto loginDto);

    CommonResponseDto<UserDto> register(SignUpDto signUpDto) throws MessagingException;

    CommonResponseDto<String> logout();

    CommonResponseDto<String> forgotPassword(EmailDto emailDto) throws MessagingException;

    CommonResponseDto<String> reSendConfirmation(EmailDto emailDto) throws MessagingException;
}
