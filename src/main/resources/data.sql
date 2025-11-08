-- Default admin user (password: admin123)
-- BCrypt hash for "admin123"
INSERT INTO users (username, email, password, role) VALUES 
('admin', 'admin@example.com', '$2a$10$MohgZBsbdp0tBoXTk4Fd3OaZRWcr5MeOgS1o67FhhZzLk4hPzPHRS', 'ADMIN');

-- Default salary types
INSERT INTO salary_types (name, hourly_rate, user_id, created_by) VALUES 
('Regular', 1300, NULL, 1),
('Premium', 1500, NULL, 1);
