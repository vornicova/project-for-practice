CREATE TABLE t_courses
(
    id          BIGSERIAL PRIMARY KEY,
    title       VARCHAR(255) NOT NULL,
    description VARCHAR(1024),
    is_active   BOOLEAN      NOT NULL DEFAULT true
);
