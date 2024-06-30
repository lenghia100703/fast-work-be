package com.fastwork.dtos.advance;

import lombok.Data;

@Data
public class EditAdvanceDto {
    private String note;
    private String description;
    private String giver;
    private String responder;
    private Long amount;
}
