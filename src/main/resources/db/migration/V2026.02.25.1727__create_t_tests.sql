CREATE TABLE t_tests(
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR,
    is_active BOOLEAN,
    lessons_id BIGINT NOT NULL,

    CONSTRAINT fk_tests_lessons
                    FOREIGN KEY (lessons_id)
                    REFERENCES t_lessons(id)
                    ON DELETE CASCADE

);
