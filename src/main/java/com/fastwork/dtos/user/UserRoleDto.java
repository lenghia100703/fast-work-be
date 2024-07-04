package com.fastwork.dtos.user;

import com.fastwork.enums.Role;
import lombok.Data;

@Data
public class UserRoleDto {
    private Long id;
    private String username;
    private String role;

    public UserRoleDto(Long id, String username, Role role) {
        this.id = id;
        this.username = username;
        System.out.println(role);
        this.role = role.toString();
    }
}
