package com.fastwork.entities;

import com.fastwork.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    private String username;

    private String phone;

    private String address;

    @Column(length = 500)
    private String description;

    private Date registrationDate;

    @Enumerated(EnumType.STRING)
    private Role role = Role.HOST;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity owner;

    @OneToMany(
            mappedBy = "construction",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<ExpenseEntity> expenses;
}
