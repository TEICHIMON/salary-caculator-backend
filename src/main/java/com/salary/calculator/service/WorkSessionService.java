package com.salary.calculator.service;

import com.salary.calculator.dto.SummaryResponse;
import com.salary.calculator.dto.WorkSessionRequest;
import com.salary.calculator.entity.WorkSession;
import com.salary.calculator.mapper.WorkSessionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class WorkSessionService {
    
    @Autowired
    private WorkSessionMapper workSessionMapper;
    
    public List<WorkSession> getByMonth(Long userId, int year, int month) {
        return workSessionMapper.findByUserIdAndMonth(userId, year, month);
    }
    
    public List<WorkSession> getByDateRange(Long userId, LocalDate startDate, LocalDate endDate) {
        return workSessionMapper.findByUserIdAndDateRange(userId, startDate, endDate);
    }
    
    public WorkSession create(WorkSessionRequest request, Long userId) {
        validateNoOverlap(userId, request.getWorkDate(), 
                         request.getStartTime().toString(), 
                         request.getEndTime().toString(), null);
        
        WorkSession workSession = new WorkSession();
        workSession.setUserId(userId);
        workSession.setWorkDate(request.getWorkDate());
        workSession.setStartTime(request.getStartTime());
        workSession.setEndTime(request.getEndTime());
        workSession.setSalaryTypeId(request.getSalaryTypeId());
        workSession.setCreatedBy(userId);
        workSession.setUpdatedBy(userId);
        
        workSessionMapper.insert(workSession);
        return workSession;
    }
    
    public WorkSession update(Long id, WorkSessionRequest request, Long userId) {
        WorkSession workSession = workSessionMapper.findById(id);
        if (workSession == null) {
            throw new RuntimeException("Work session not found");
        }
        
        validateNoOverlap(userId, request.getWorkDate(), 
                         request.getStartTime().toString(), 
                         request.getEndTime().toString(), id);
        
        workSession.setWorkDate(request.getWorkDate());
        workSession.setStartTime(request.getStartTime());
        workSession.setEndTime(request.getEndTime());
        workSession.setSalaryTypeId(request.getSalaryTypeId());
        workSession.setUpdatedBy(userId);
        
        workSessionMapper.update(workSession);
        return workSession;
    }
    
    public void delete(Long id) {
        workSessionMapper.delete(id);
    }
    
    public SummaryResponse getSummary(Long userId, LocalDate startDate, LocalDate endDate) {
        List<Map<String, Object>> result = workSessionMapper.getSummary(userId, startDate, endDate);
        SummaryResponse response = new SummaryResponse();
        
        if (!result.isEmpty()) {
            Map<String, Object> data = result.get(0);
            response.setWorkDays(((Number) data.get("work_days")).intValue());
            
            Object totalHours = data.get("total_hours");
            response.setTotalHours(totalHours != null ? 
                new BigDecimal(totalHours.toString()) : BigDecimal.ZERO);
            
            Object totalSalary = data.get("total_salary");
            response.setTotalSalary(totalSalary != null ? 
                new BigDecimal(totalSalary.toString()) : BigDecimal.ZERO);
        } else {
            response.setWorkDays(0);
            response.setTotalHours(BigDecimal.ZERO);
            response.setTotalSalary(BigDecimal.ZERO);
        }
        
        return response;
    }
    
    private void validateNoOverlap(Long userId, LocalDate workDate, String startTime, String endTime, Long excludeId) {
        List<WorkSession> overlapping = workSessionMapper.findOverlappingSessions(
            userId, workDate, startTime, endTime, excludeId);
        
        if (!overlapping.isEmpty()) {
            throw new RuntimeException("Time period overlaps with existing work session");
        }
    }
}
