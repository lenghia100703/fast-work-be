package com.fastwork.controllers;

import com.fastwork.dtos.common.CommonResponseDto;
import com.fastwork.dtos.common.PaginatedDataDto;
import com.fastwork.dtos.user.AddUserDto;
import com.fastwork.dtos.user.ChangePasswordDto;
import com.fastwork.dtos.user.UserDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
