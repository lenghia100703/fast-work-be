package com.fastwork.entities;

import com.fastwork.enums.Shift;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity employee;
}
