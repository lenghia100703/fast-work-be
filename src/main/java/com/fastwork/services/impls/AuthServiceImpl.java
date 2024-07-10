package com.fastwork.services.impls;

import com.fastwork.dtos.auth.*;
import com.fastwork.dtos.common.CommonResponseDto;
import com.fastwork.dtos.mail.MailConfirmDto;
import com.fastwork.dtos.user.UserDto;
import com.fastwork.dtos.user.UserInfoInToken;
import com.fastwork.entities.UserEntity;
import com.fastwork.entities.VerificationTokenEntity;
import com.fastwork.enums.ResponseCode;
import com.fastwork.enums.Role;
import com.fastwork.enums.TypeToken;
import com.fastwork.exceptions.CommonException;
import com.fastwork.repositories.UserRepository;
import com.fastwork.repositories.VerificationTokenRepository;
import com.fastwork.securities.JWTProvider;
import com.fastwork.services.AuthService;
import com.fastwork.services.MailService;
import com.fastwork.utils.SecurityContextUtil;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static com.fastwork.enums.AuthProvider.LOCAL;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @Autowired
    JWTProvider jwtProvider;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MailService mailService;

    @Value("${default.avatar}")
    String defaultAvatar;

    private final HttpServletResponse response;

    public AuthServiceImpl(HttpServletResponse response) {
        this.response = response;
    }


    @Override
    public CommonResponseDto<AuthResponseDto> login(LoginDto loginDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );

        Optional<UserEntity> optionalUser = userRepository.findUserByEmail(loginDto.getEmail());

        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();

            if (!user.getEmailConfirmed()) {
                return new CommonResponseDto<>(ResponseCode.EMAIL_NOT_CONFIRMED);
            }

            String accessToken = jwtProvider.generateAccessToken(response, new UserInfoInToken(user.getId()), String.valueOf(user.getRole()));
            String refreshToken = jwtProvider.generateRefreshToken(response, new UserInfoInToken(user.getId()), String.valueOf(user.getRole()));
            user.setAccessToken(accessToken);
            user.setRefreshToken(refreshToken);
            AuthResponseDto authResponse = new AuthResponseDto(user.getId(), accessToken, refreshToken);
            userRepository.save(user);
            return new CommonResponseDto<>(authResponse);
        } else {
            return new CommonResponseDto<>(ResponseCode.ERROR);
        }
    }

    @Override
    public CommonResponseDto<UserDto> register(SignUpDto signUpDto) throws MessagingException {
        if (userRepository.findUserByEmail(signUpDto.getEmail()).isPresent()) {
            throw new CommonException(ResponseCode.ERROR, "Email đã tồn tại");
        }

        UserEntity user = new UserEntity();
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        user.setEmail(signUpDto.getEmail());
        user.setUsername(signUpDto.getUsername());
        user.setRole(Role.valueOf(signUpDto.getRole()));
        user.setAvatar(defaultAvatar);
        user.setCreatedAt(new Date(System.currentTimeMillis()));
        user.setCreatedBy(signUpDto.getEmail());
        user.setProvider(LOCAL);
        userRepository.save(user);
        VerificationTokenEntity verificationToken = createVerificationToken(user, TypeToken.CONFIRM);
        mailService.sendConfirmationEmail(new MailConfirmDto(verificationToken));

        return new CommonResponseDto<>(new UserDto(userRepository.save(user)));
    }

    @Override
    public CommonResponseDto<String> logout() {
        Long id = SecurityContextUtil.getCurrentUserId();
        UserEntity currentUser = userRepository.findById(id).get();
        currentUser.setAccessToken(null);
        currentUser.setRefreshToken(null);
        userRepository.save(currentUser);
        SecurityContextHolder.clearContext();

        ResponseCookie jwtCookie = ResponseCookie.from("jwt", null)
                .maxAge(1000)
                .httpOnly(true).path("/").secure(true).sameSite("None").build();
        response.addHeader(HttpHeaders.SET_COOKIE, jwtCookie.toString());

        ResponseCookie jwtRefreshCookie = ResponseCookie.from("jwt-refresh", null)
                .maxAge(1000)
                .httpOnly(true).path("/").secure(true).sameSite("None").build();
        response.addHeader(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString());

        return new CommonResponseDto<>("Logged out successfully");
    }

    @Override
    public CommonResponseDto<String> forgotPassword(EmailDto emailDto) throws MessagingException {
        UserEntity user = userRepository.findUserByEmail(emailDto.getEmail()).get();
        VerificationTokenEntity verificationToken = createVerificationToken(user, TypeToken.FORGOT);
        mailService.sendForgotPasswordEmail(new MailConfirmDto(verificationToken));

        return new CommonResponseDto<>("Send reset password successfully");
    }

    @Override
    public CommonResponseDto<String> reSendConfirmation(EmailDto emailDto) throws MessagingException {
        UserEntity user = userRepository.findUserByEmail(emailDto.getEmail()).get();
        VerificationTokenEntity verificationToken = createVerificationToken(user, TypeToken.CONFIRM);
        mailService.sendConfirmationEmail(new MailConfirmDto(verificationToken));

        return new CommonResponseDto<>("Resend confirmation email successfully");
    }

    @Override
    public CommonResponseDto<String> confirmRegistration(ConfirmToken confirmToken) {
        VerificationTokenEntity token = verificationTokenRepository.findVerificationTokenByToken(confirmToken.getToken()).orElse(null);

        if (token == null) {
            return new CommonResponseDto<>(ResponseCode.NOT_FOUND);
        }

        if (token.getExpiryDate().before(new Date(System.currentTimeMillis()))) {
            return new CommonResponseDto<>(ResponseCode.TOKEN_HAS_EXPIRED);
        }

        UserEntity user = userRepository.getById(token.getUser().getId());
        user.setEmailConfirmed(true);
        userRepository.save(user);
        return new CommonResponseDto<>("Confirm registration successfully");
    }

    private VerificationTokenEntity createVerificationToken(UserEntity user, TypeToken type) {
        String token = UUID.randomUUID().toString().replace("-", "");
        VerificationTokenEntity myToken = new VerificationTokenEntity(token, user, type);
        myToken.setCreatedAt(new Date(System.currentTimeMillis()));
        return verificationTokenRepository.save(myToken);
    }
}
