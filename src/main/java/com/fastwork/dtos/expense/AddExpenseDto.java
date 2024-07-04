package com.fastwork.dtos.expense;

import lombok.Data;

@Data
public class AddExpenseDto {
    private String title;
    private String description;
    private String sellerPhone;
    private int quantity;
    private Long price;
    private Long constructionId;
}
