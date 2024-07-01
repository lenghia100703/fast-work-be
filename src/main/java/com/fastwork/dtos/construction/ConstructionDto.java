package com.fastwork.dtos.construction;

import com.fastwork.dtos.expense.ExpenseDto;
import com.fastwork.entities.ConstructionEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ConstructionDto {
    private Long id;
    private String username;
    private String phone;
    private String address;
    private String description;
    private Date registrationDate;
    private List<ExpenseDto> expenses;
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
        this.expenses = construction.getExpenses().stream().map(ExpenseDto::new).toList();
        this.createdAt = construction.getCreatedAt();
        this.updatedAt = construction.getUpdatedAt();
        this.createdBy = construction.getCreatedBy();
        this.updatedBy = construction.getUpdatedBy();
    }
}
