package com.salary.calculator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.salary.calculator.mapper")
public class SalaryCalculatorApplication {
    public static void main(String[] args) {
        SpringApplication.run(SalaryCalculatorApplication.class, args);
    }
}
