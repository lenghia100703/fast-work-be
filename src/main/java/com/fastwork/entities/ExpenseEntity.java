package com.fastwork.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Table(name = "expense")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String sellerPhone;

    private int quantity;

    private Long price;

    private Long totalPrice;

    private Date purchaseDate;

    @ManyToOne
    @JoinColumn(name = "construction_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ConstructionEntity construction;
}
