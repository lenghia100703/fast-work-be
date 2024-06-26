package com.fastwork.dtos.mail;

import com.fastwork.dtos.user.UserDto;
import com.fastwork.entities.VerificationTokenEntity;
import lombok.Data;

import java.util.Date;

@Data
public class MailConfirmDto {
    private Long id;
    private String token;
    private UserDto user;
    private Date expiryDate;
    private String type;
    private Date createdAt;
    private Date updatedAt;
    private String createdBy;
    private String updatedBy;

    public MailConfirmDto(VerificationTokenEntity verificationToken) {
        this.id = verificationToken.getId();
        this.token = verificationToken.getToken();
        this.user = new UserDto(verificationToken.getUser());
        this.expiryDate = verificationToken.getExpiryDate();
        this.type = verificationToken.getType().toString();
        this.createdAt = verificationToken.getCreatedAt();
        this.updatedAt = verificationToken.getUpdatedAt();
        this.createdBy = verificationToken.getCreatedBy();
        this.updatedBy = verificationToken.getUpdatedBy();
    }
}
