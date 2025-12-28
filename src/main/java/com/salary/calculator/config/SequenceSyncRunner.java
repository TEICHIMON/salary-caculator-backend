package com.salary.calculator.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class SequenceSyncRunner implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(SequenceSyncRunner.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 表名和对应的序列名
    private static final String[][] TABLE_SEQUENCES = {
            {"users", "users_id_seq"},
            {"salary_types", "salary_types_id_seq"},
            {"work_sessions", "work_sessions_id_seq"}
    };

    @Override
    public void run(ApplicationArguments args) {
        log.info("Syncing database sequences...");

        for (String[] pair : TABLE_SEQUENCES) {
            syncSequence(pair[0], pair[1]);
        }

        log.info("Sequence sync completed.");
    }

    private void syncSequence(String tableName, String sequenceName) {
        try {
            String sql = String.format(
                    "SELECT setval('%s', COALESCE((SELECT MAX(id) FROM %s), 0) + 1, false)",
                    sequenceName, tableName
            );
            Long newValue = jdbcTemplate.queryForObject(sql, Long.class);
            log.info("Synced sequence {} to {}", sequenceName, newValue);
        } catch (Exception e) {
            log.warn("Failed to sync sequence {}: {}", sequenceName, e.getMessage());
        }
    }
}
