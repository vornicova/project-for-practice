CREATE TABLE t_answers (
    id          BIGSERIAL PRIMARY KEY,
    text        VARCHAR(255) NOT NULL,
    is_correct  BOOLEAN      NOT NULL,
    question_id BIGINT       NOT NULL,

    CONSTRAINT fk_answers_question
        FOREIGN KEY (question_id)
            REFERENCES t_questions (id)
            ON DELETE CASCADE
);
