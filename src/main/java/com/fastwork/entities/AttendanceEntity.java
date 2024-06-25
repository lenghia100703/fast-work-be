package com.fastwork.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fastwork.enums.Shift;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "attendance")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate workDate;

    @Enumerated(EnumType.STRING)
    private Shift shift;

    private String note;

    private int hoursWorked;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private UserEntity employee;
}
