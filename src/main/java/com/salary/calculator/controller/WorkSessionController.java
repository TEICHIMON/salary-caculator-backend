package com.salary.calculator.controller;

import com.salary.calculator.dto.SummaryResponse;
import com.salary.calculator.dto.WorkSessionRequest;
import com.salary.calculator.entity.WorkSession;
import com.salary.calculator.security.JwtUtil;
import com.salary.calculator.service.WorkSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/work-sessions")
public class WorkSessionController {
    
    @Autowired
    private WorkSessionService workSessionService;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @GetMapping
    public ResponseEntity<List<WorkSession>> getWorkSessions(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer month,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestHeader("Authorization") String token) {
        
        Long userId = jwtUtil.extractUserId(token.substring(7));
        
        if (year != null && month != null) {
            return ResponseEntity.ok(workSessionService.getByMonth(userId, year, month));
        } else if (startDate != null && endDate != null) {
            return ResponseEntity.ok(workSessionService.getByDateRange(userId, startDate, endDate));
        }
        
        return ResponseEntity.badRequest().build();
    }
    
    @PostMapping
    public ResponseEntity<WorkSession> create(@RequestBody WorkSessionRequest request,
                                               @RequestHeader("Authorization") String token) {
        Long userId = jwtUtil.extractUserId(token.substring(7));
        return ResponseEntity.ok(workSessionService.create(request, userId));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<WorkSession> update(@PathVariable Long id,
                                               @RequestBody WorkSessionRequest request,
                                               @RequestHeader("Authorization") String token) {
        Long userId = jwtUtil.extractUserId(token.substring(7));
        return ResponseEntity.ok(workSessionService.update(id, request, userId));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        workSessionService.delete(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/summary")
    public ResponseEntity<SummaryResponse> getSummary(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestHeader("Authorization") String token) {
        
        Long userId = jwtUtil.extractUserId(token.substring(7));
        return ResponseEntity.ok(workSessionService.getSummary(userId, startDate, endDate));
    }
}
