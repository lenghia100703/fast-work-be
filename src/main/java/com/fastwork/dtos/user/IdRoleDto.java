package com.fastwork.dtos.user;

import lombok.Data;

@Data
public class IdRoleDto {
    private Long id;
    private String role;

    public IdRoleDto(Long id, String role) {
        this.id = id;
        this.role = role.toUpperCase();
    }
}
