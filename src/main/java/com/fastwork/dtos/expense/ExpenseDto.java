package com.fastwork.dtos.expense;

import com.fastwork.entities.ExpenseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class ExpenseDto {
    private Long id;
    private String title;
    private String description;
    private String sellerPhone;
    private int quantity;
    private Long price;
    private Long totalPrice;
    private Date purchaseDate;
    private Date createdAt;
    private Date updatedAt;
    private String createdBy;
    private String updatedBy;

    public ExpenseDto(ExpenseEntity expense) {
        this.id = expense.getId();
        this.title = expense.getTitle();
        this.description = expense.getDescription();
        this.sellerPhone = expense.getSellerPhone();
        this.quantity = expense.getQuantity();
        this.price = expense.getPrice();
        this.totalPrice = expense.getTotalPrice();
        this.purchaseDate = expense.getPurchaseDate();
        this.createdAt = expense.getCreatedAt();
        this.updatedAt = expense.getUpdatedAt();
        this.createdBy = expense.getCreatedBy();
        this.updatedBy = expense.getUpdatedBy();
    }
}
