CREATE TABLE t_lessons
(
    id        BIGSERIAL PRIMARY KEY,
    name      VARCHAR NOT NULL,
    content   VARCHAR NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    course_id BIGINT  NOT NULL REFERENCES t_courses (id)
);
