package com.fastwork.dtos.expense;

import lombok.Data;

@Data
public class EditExpenseDto {
    private String title;
    private String description;
    private String sellerPhone;
    private int quantity;
    private Long price;
}
