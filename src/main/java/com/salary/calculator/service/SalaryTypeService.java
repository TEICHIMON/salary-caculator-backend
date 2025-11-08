package com.salary.calculator.service;

import com.salary.calculator.dto.SalaryTypeRequest;
import com.salary.calculator.entity.SalaryType;
import com.salary.calculator.mapper.SalaryTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SalaryTypeService {
    
    @Autowired
    private SalaryTypeMapper salaryTypeMapper;
    
    public List<SalaryType> getAllForUser(Long userId) {
        return salaryTypeMapper.findByUserId(userId);
    }
    
    public SalaryType create(SalaryTypeRequest request, Long userId) {
        SalaryType salaryType = new SalaryType();
        salaryType.setName(request.getName());
        salaryType.setHourlyRate(request.getHourlyRate());
        salaryType.setUserId(userId);
        salaryType.setIsActive(true);
        salaryType.setCreatedBy(userId);
        salaryType.setUpdatedBy(userId);
        
        salaryTypeMapper.insert(salaryType);
        return salaryType;
    }
    
    public SalaryType update(Long id, SalaryTypeRequest request, Long userId) {
        SalaryType salaryType = salaryTypeMapper.findById(id);
        if (salaryType == null) {
            throw new RuntimeException("Salary type not found");
        }
        
        salaryType.setName(request.getName());
        salaryType.setHourlyRate(request.getHourlyRate());
        salaryType.setUpdatedBy(userId);
        
        salaryTypeMapper.update(salaryType);
        return salaryType;
    }
    
    public void delete(Long id) {
        salaryTypeMapper.delete(id);
    }
}
