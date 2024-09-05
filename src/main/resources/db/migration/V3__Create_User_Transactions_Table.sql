CREATE TABLE user_transactions (
  transaction_id BIGINT NOT NULL,
  user_id BIGINT NOT NULL,
  PRIMARY KEY (transaction_id, user_id),
  CONSTRAINT fk_transaction_id FOREIGN KEY (transaction_id) REFERENCES transactions(id),
  CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(id)
);