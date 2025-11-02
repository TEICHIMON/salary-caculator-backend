package com.salary.calculator.dto;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class WorkSessionRequest {
    private LocalDate workDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long salaryTypeId;
}
