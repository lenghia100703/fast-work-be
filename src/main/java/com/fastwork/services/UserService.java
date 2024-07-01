package com.fastwork.services;

import com.fastwork.dtos.common.CommonResponseDto;
import com.fastwork.dtos.common.PaginatedDataDto;
import com.fastwork.dtos.user.*;
import com.fastwork.entities.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {
    CommonResponseDto<UserDto> getUserById(Long id);

    UserEntity getCurrentUser();

    PaginatedDataDto<UserDto> getUserByPage(int page);

    CommonResponseDto<UserDto> createUser(AddUserDto addUserDto);

    CommonResponseDto<String> editUser(Long id, String email, String username, String phone, String address, String age,
                                              String avatarUrl, MultipartFile file) throws IOException;

    CommonResponseDto<String> deleteUser(Long id);

    CommonResponseDto<String> changePassword(Long id, ChangePasswordDto changePasswordDto);

    CommonResponseDto<List<UserRoleDto>> getUserRole();

    CommonResponseDto<?> getByIdAndRole(IdRoleDto idRoleDto);

    UserEntity findByEmail(String email);

    Long getCurrentUserId();
}
