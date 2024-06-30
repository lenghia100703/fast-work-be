package com.fastwork.dtos.advance;

import com.fastwork.dtos.user.UserDto;
import com.fastwork.entities.AdvanceEntity;
import lombok.Data;

import java.util.Date;

@Data
public class AdvanceDto {
    private Long id;
    private Date advanceDate;
    private String description;
    private String note;
    private UserDto owner;
    private String giver;
    private String responder;
    private Date createdAt;
    private Date updatedAt;
    private String createdBy;
    private String updatedBy;

    public AdvanceDto(AdvanceEntity advance) {
        this.id = advance.getId();
        this.advanceDate = advance.getAdvanceDate();
        this.description = advance.getDescription();
        this.note = advance.getNote();
        this.owner = new UserDto(advance.getOwner());
        this.giver = advance.getGiver();
        this.responder = advance.getResponder();
        this.createdAt = advance.getCreatedAt();
        this.updatedAt = advance.getUpdatedAt();
        this.createdBy = advance.getCreatedBy();
        this.updatedBy = advance.getUpdatedBy();
    }
}
