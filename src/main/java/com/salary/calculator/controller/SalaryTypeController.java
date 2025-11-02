package com.salary.calculator.controller;

import com.salary.calculator.dto.SalaryTypeRequest;
import com.salary.calculator.entity.SalaryType;
import com.salary.calculator.security.JwtUtil;
import com.salary.calculator.service.SalaryTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salary-types")
public class SalaryTypeController {
    
    @Autowired
    private SalaryTypeService salaryTypeService;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @GetMapping
    public ResponseEntity<List<SalaryType>> getAll(@RequestHeader("Authorization") String token) {
        Long userId = jwtUtil.extractUserId(token.substring(7));
        return ResponseEntity.ok(salaryTypeService.getAllForUser(userId));
    }
    
    @PostMapping
    public ResponseEntity<SalaryType> create(@RequestBody SalaryTypeRequest request, 
                                              @RequestHeader("Authorization") String token) {
        Long userId = jwtUtil.extractUserId(token.substring(7));
        return ResponseEntity.ok(salaryTypeService.create(request, userId));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<SalaryType> update(@PathVariable Long id, 
                                              @RequestBody SalaryTypeRequest request,
                                              @RequestHeader("Authorization") String token) {
        Long userId = jwtUtil.extractUserId(token.substring(7));
        return ResponseEntity.ok(salaryTypeService.update(id, request, userId));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        salaryTypeService.delete(id);
        return ResponseEntity.ok().build();
    }
}
