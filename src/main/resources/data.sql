-- Default admin user (password: admin123)
-- BCrypt hash for "admin123"
INSERT INTO users (username, email, password, role) VALUES 
('admin', 'admin@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ADMIN');

-- Default salary types
INSERT INTO salary_types (name, hourly_rate, user_id, created_by) VALUES 
('Regular', 1300, NULL, 1),
('Premium', 1500, NULL, 1);
