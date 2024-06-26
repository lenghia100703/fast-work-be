package com.fastwork.dtos.attendance;

import com.fastwork.dtos.user.UserDto;
import com.fastwork.entities.AttendanceEntity;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class AttendanceDto {
    private Long id;
    private LocalDate workDate;
    private String shift;
    private String note;
    private int hoursWorked;
    private UserDto employee;
    private Date createdAt;
    private Date updatedAt;
    private String createdBy;
    private String updatedBy;

    public AttendanceDto(AttendanceEntity attendance) {
        this.id = attendance.getId();
        this.workDate = attendance.getWorkDate();
        this.shift = attendance.getShift().toString();
        this.note = attendance.getNote();
        this.hoursWorked = attendance.getHoursWorked();
        this.employee = new UserDto(attendance.getEmployee());
        this.createdAt = attendance.getCreatedAt();
        this.updatedAt = attendance.getUpdatedAt();
        this.createdBy = attendance.getCreatedBy();
        this.updatedBy = attendance.getUpdatedBy();
    }
}
