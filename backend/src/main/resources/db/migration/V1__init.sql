CREATE TABLE policy_checks (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    policy_name VARCHAR(255),
    input_text TEXT,
    compliance_status VARCHAR(50),
    deleted BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);