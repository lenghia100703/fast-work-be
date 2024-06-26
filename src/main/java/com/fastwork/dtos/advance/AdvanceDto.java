package com.fastwork.dtos.advance;

import com.fastwork.dtos.user.UserDto;
import com.fastwork.entities.AdvanceEntity;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class AdvanceDto {
    private Long id;
    private LocalDate advanceDate;
    private String description;
    private String note;
    private UserDto employee;
    private Date createdAt;
    private Date updatedAt;
    private String createdBy;
    private String updatedBy;

    public AdvanceDto(AdvanceEntity advance) {
        this.id = advance.getId();
        this.advanceDate = advance.getAdvanceDate();
        this.description = advance.getDescription();
        this.note = advance.getNote();
        this.employee = new UserDto(advance.getEmployee());
        this.createdAt = advance.getCreatedAt();
        this.updatedAt = advance.getUpdatedAt();
        this.createdBy = advance.getCreatedBy();
        this.updatedBy = advance.getUpdatedBy();
    }
}
