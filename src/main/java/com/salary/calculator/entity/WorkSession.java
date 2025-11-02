package com.salary.calculator.entity;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

@Data
public class WorkSession {
    private Long id;
    private Long userId;
    private LocalDate workDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long salaryTypeId;
    private LocalDateTime createdAt;
    private Long createdBy;
    private LocalDateTime updatedAt;
    private Long updatedBy;
}
