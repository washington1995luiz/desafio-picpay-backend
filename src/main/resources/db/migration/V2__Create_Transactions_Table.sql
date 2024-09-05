CREATE TABLE transactions (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_sender VARCHAR(255) NOT NULL,
  user_receive VARCHAR(255) NOT NULL,
  timestamp DATETIME,
  value DECIMAL(10, 2) NOT NULL
);