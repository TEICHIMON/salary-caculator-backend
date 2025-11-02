package com.salary.calculator.mapper;

import com.salary.calculator.entity.SalaryType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface SalaryTypeMapper {
    List<SalaryType> findByUserId(@Param("userId") Long userId);
    List<SalaryType> findAllActive();
    SalaryType findById(@Param("id") Long id);
    void insert(SalaryType salaryType);
    void update(SalaryType salaryType);
    void delete(@Param("id") Long id);
}
