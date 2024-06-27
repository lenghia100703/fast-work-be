package com.fastwork.controllers.impls;

import com.fastwork.controllers.AuthController;
import com.fastwork.dtos.auth.AuthResponseDto;
import com.fastwork.dtos.auth.EmailDto;
import com.fastwork.dtos.auth.LoginDto;
import com.fastwork.dtos.auth.SignUpDto;
import com.fastwork.dtos.common.CommonResponseDto;
import com.fastwork.dtos.user.UserDto;
import com.fastwork.services.AuthService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthControllerImpl implements AuthController {
    @Autowired
    AuthService authService;

    @Override
    public CommonResponseDto<UserDto> register(SignUpDto signUpDto) throws MessagingException {
        return authService.register(signUpDto);
    }

    @Override
    public CommonResponseDto<AuthResponseDto> login(LoginDto loginDto) {
        return authService.login(loginDto);
    }

    @Override
    public CommonResponseDto<String> logout() {
        return authService.logout();
    }

    @Override
    public CommonResponseDto<String> forgotPassword(EmailDto emailDto) throws MessagingException {
        return authService.forgotPassword(emailDto);
    }

    @Override
    public CommonResponseDto<String> reSendConfirmation(EmailDto emailDto) throws MessagingException {
        return authService.reSendConfirmation(emailDto);
    }
}
