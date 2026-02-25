CREATE TABLE t_tests_questions
(
    test_id     BIGINT NOT NULL,
    question_id BIGINT NOT NULL,

    CONSTRAINT pk_tests_questions PRIMARY KEY (test_id, question_id),

    CONSTRAINT fk_tests_questions_test
        FOREIGN KEY (test_id)
            REFERENCES t_tests (id)
            ON DELETE CASCADE,

    CONSTRAINT fk_tests_questions_question
        FOREIGN KEY (question_id)
            REFERENCES t_questions (id)
            ON DELETE CASCADE
);
