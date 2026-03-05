CREATE TABLE t_users
(
    id         BIGINT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name  VARCHAR(100) NOT NULL,
    is_active  BOOLEAN DEFAULT TRUE,
    email      VARCHAR(255) NOT NULL UNIQUE
);