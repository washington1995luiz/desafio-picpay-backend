CREATE TABLE users (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,  -- Adjust based on your strategy (IDENTITY vs SEQUENCE)
  first_name VARCHAR(80) NOT NULL,
  last_name VARCHAR(80) NOT NULL,
  document VARCHAR(14) NOT NULL UNIQUE,
  email VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(16) NOT NULL,
  account_type ENUM('COMMON_USER', 'BUSINESS_USER') NOT NULL,
  balance DECIMAL(10, 2)
);