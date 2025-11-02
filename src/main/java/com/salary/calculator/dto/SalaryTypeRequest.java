package com.salary.calculator.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class SalaryTypeRequest {
    private String name;
    private BigDecimal hourlyRate;
}
