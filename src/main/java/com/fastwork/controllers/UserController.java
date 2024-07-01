package com.fastwork.controllers;

import com.fastwork.dtos.common.CommonResponseDto;
import com.fastwork.dtos.common.PaginatedDataDto;
import com.fastwork.dtos.user.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/api/user")
public interface UserController {
    @GetMapping("/{id}")
    CommonResponseDto<UserDto> getUserById(@PathVariable("id") Long id);

    @GetMapping("/me")
    CommonResponseDto<UserDto> getCurrentUser();

    @GetMapping("")
    PaginatedDataDto<UserDto> getUserByPage(@RequestParam(name = "page") int page);

    @PostMapping("")
    CommonResponseDto<UserDto> createUser(@RequestBody AddUserDto addUserDto);

    @GetMapping("/role")
    CommonResponseDto<List<UserRoleDto>> getUserRole();

    @GetMapping("/{role}/{id}")
    CommonResponseDto<?> getByIdAndRole(@PathVariable("id") Long id, @PathVariable("role") String role);

    @PutMapping("/{id}")
    CommonResponseDto<String> editUser(@PathVariable("id") Long id,
                                       @RequestParam(value = "avatar", required = false) MultipartFile file,
                                       @RequestParam("username") String username,
                                       @RequestParam("email") String email,
                                       @RequestParam("age") String age,
                                       @RequestParam("phone") String phone,
                                       @RequestParam("address") String address,
                                       @RequestParam("avatarUrl") String avatarUrl) throws IOException;


    @DeleteMapping("/{id}")
    CommonResponseDto<String> deleteUser(@PathVariable("id") Long id);

    @PutMapping("/change-password/{id}")
    CommonResponseDto<String> changePassword(@PathVariable("id") Long id, @RequestBody ChangePasswordDto changePasswordDto);

}
