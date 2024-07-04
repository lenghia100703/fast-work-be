package com.fastwork.dtos.construction;

import com.fastwork.dtos.attendance.AttendanceDto;
import com.fastwork.dtos.expense.ExpenseDto;
import com.fastwork.dtos.user.UserDto;
import com.fastwork.entities.ConstructionEntity;
import lombok.Data;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Data
public class ConstructionDto {
    private Long id;
    private String username;
    private String phone;
    private String address;
    private String description;
    private Date registrationDate;
    private List<ExpenseDto> expenses;
    private UserDto owner;
    private Date createdAt;
    private Date updatedAt;
    private String createdBy;
    private String updatedBy;

    public ConstructionDto(ConstructionEntity construction) {
        this.id = construction.getId();
        this.username = construction.getUsername();
        this.phone = construction.getPhone();
        this.address = construction.getAddress();
        this.description = construction.getDescription();
        this.registrationDate = construction.getRegistrationDate();
        this.expenses = Optional.ofNullable(construction.getExpenses())
                .orElse(Collections.emptyList())
                .stream()
                .map(ExpenseDto::new)
                .toList();
        this.owner = new UserDto(construction.getOwner());
        this.createdAt = construction.getCreatedAt();
        this.updatedAt = construction.getUpdatedAt();
        this.createdBy = construction.getCreatedBy();
        this.updatedBy = construction.getUpdatedBy();
    }
}
