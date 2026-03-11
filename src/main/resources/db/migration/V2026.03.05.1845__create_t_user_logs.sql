CREATE TABLE t_user_logs
(
    id        BIGSERIAL PRIMARY KEY,
    user_id   BIGINT           NOT NULL,
    action    user_action_type NOT NULL,
    timestamp TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_user_logs_user_id
        FOREIGN KEY (user_id)
            REFERENCES t_users (id)

);
