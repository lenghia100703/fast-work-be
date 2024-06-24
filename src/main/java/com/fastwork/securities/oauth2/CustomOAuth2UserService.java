package com.fastwork.securities.oauth2;

import com.fastwork.entities.UserEntity;
import com.fastwork.enums.AuthProvider;
import com.fastwork.exceptions.CommonException;
import com.fastwork.repositories.UserRepository;
import com.fastwork.securities.UserPrincipal;
import com.fastwork.securities.oauth2.user.OAuth2UserInfo;
import com.fastwork.securities.oauth2.user.OAuth2UserInfoFactory;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

import static com.fastwork.enums.ResponseCode.USER_IS_DELETE;


@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final HttpServletResponse response;
    @Autowired
    ClientRegistrationRepository clientRegistrationRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public CustomOAuth2UserService(HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
        if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new CommonException();
        }

        Optional<UserEntity> userOptional = userRepository.findUserByEmail(oAuth2UserInfo.getEmail());
        UserEntity user = userOptional.orElse(null);
        if (user != null) {
            if (!user.getActive()) {
                throw new CommonException(USER_IS_DELETE, "Người dùng đã bị xoá bởi quản trị viên.");
            }
            if (user.getProvider().equals(AuthProvider.LOCAL)) {
                user = verifyEmail(user);
            }
            user = updateExistingUser(user, oAuth2UserInfo);
        } else {
            user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
        }

        return UserPrincipal.create(user, oAuth2User.getAttributes());
    }

    private UserEntity verifyEmail(UserEntity existingUser) {
        existingUser.setEmailConfirmed(true);
        return userRepository.save(existingUser);
    }

    private UserEntity registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
        UserEntity user = new UserEntity();

        user.setProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId().toUpperCase()));
        user.setProviderId(oAuth2UserInfo.getId());
        user.setUsername(oAuth2UserInfo.getName());
        user.setEmail(oAuth2UserInfo.getEmail());
        user.setAvatar(oAuth2UserInfo.getImageUrl());
        user.setActive(true);
        user.setEmailConfirmed(true);

        UserEntity res = userRepository.save(user);
        return res;
    }

    private UserEntity updateExistingUser(UserEntity existingUser, OAuth2UserInfo oAuth2UserInfo) {
        if (existingUser.getUsername() == null || existingUser.getUsername().equals(""))
            existingUser.setUsername(oAuth2UserInfo.getName());
        if (existingUser.getAvatar() == null || existingUser.getAvatar().equals(""))
            existingUser.setAvatar(oAuth2UserInfo.getImageUrl());
        return userRepository.save(existingUser);
    }

}
