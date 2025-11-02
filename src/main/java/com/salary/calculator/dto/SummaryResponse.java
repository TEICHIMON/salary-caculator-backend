package com.salary.calculator.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class SummaryResponse {
    private Integer workDays;
    private BigDecimal totalHours;
    private BigDecimal totalSalary;
}
