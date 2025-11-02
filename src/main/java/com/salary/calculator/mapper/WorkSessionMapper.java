package com.salary.calculator.mapper;

import com.salary.calculator.entity.WorkSession;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface WorkSessionMapper {
    List<WorkSession> findByUserIdAndMonth(@Param("userId") Long userId, 
                                            @Param("year") int year, 
                                            @Param("month") int month);
    List<WorkSession> findByUserIdAndDateRange(@Param("userId") Long userId, 
                                                 @Param("startDate") LocalDate startDate, 
                                                 @Param("endDate") LocalDate endDate);
    WorkSession findById(@Param("id") Long id);
    void insert(WorkSession workSession);
    void update(WorkSession workSession);
    void delete(@Param("id") Long id);
    List<WorkSession> findOverlappingSessions(@Param("userId") Long userId,
                                                @Param("workDate") LocalDate workDate,
                                                @Param("startTime") String startTime,
                                                @Param("endTime") String endTime,
                                                @Param("excludeId") Long excludeId);
    List<Map<String, Object>> getSummary(@Param("userId") Long userId,
                                          @Param("startDate") LocalDate startDate,
                                          @Param("endDate") LocalDate endDate);
}
