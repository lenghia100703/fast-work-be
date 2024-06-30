package com.fastwork.dtos.advance;

import lombok.Data;

@Data
public class AddAdvanceDto {
    private String note;
    private String description;
    private Long amount;
    private String giver;
    private String responder;
    private Long ownerId;
}
