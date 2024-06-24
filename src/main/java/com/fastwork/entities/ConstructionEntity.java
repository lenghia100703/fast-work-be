package com.fastwork.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "construction")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConstructionEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String hostName;

    private String phone;

    private String address;

    private String description;

    private Date registrationDate;

    @OneToMany(
            mappedBy = "construction",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<ExpenseEntity> expenses;
}
