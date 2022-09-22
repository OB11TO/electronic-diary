--liquibase formatted sql

--changeset obito:1
CREATE TABLE IF NOT EXISTS student
(
    id               BIGSERIAL PRIMARY KEY,
    name             VARCHAR(128) NOT NULL,
    address          VARCHAR(255) NOT NULL,
    phone            VARCHAR(64)  NOT NULL UNIQUE,
    email            VARCHAR(64)  NOT NULL UNIQUE,
    gradebook_number INT          NOT NULL UNIQUE
);

--changeset obito:2
CREATE TABLE IF NOT EXISTS teacher
(
    id      BIGSERIAL PRIMARY KEY,
    name    VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    phone   VARCHAR(64)  NOT NULL UNIQUE,
    payment FLOAT        NOT NULL

);

--changeset obito:3
CREATE TABLE IF NOT EXISTS course
(
    id         BIGSERIAL PRIMARY KEY,
    title      VARCHAR(128) NOT NULL,
    number     INT          NOT NULL UNIQUE,
    cost       FLOAT        NOT NULL,
    teacher_id BIGINT REFERENCES teacher (id)
);

--changeset obito:4
CREATE TABLE IF NOT EXISTS students_course
(
    id         BIGSERIAL PRIMARY KEY,
    student_id BIGINT NOT NULL REFERENCES student (id) ON DELETE CASCADE,
    course_id  BIGINT NOT NULL REFERENCES course (id) ON DELETE CASCADE,
    UNIQUE (student_id, course_id)
);

--changeset obito:5
CREATE TABLE IF NOT EXISTS grades
(
    id                 BIGSERIAL,
    students_course_id BIGINT NOT NULL REFERENCES students_course (id) ON DELETE CASCADE,
    grade              INT,
    PRIMARY KEY (students_course_id, id)
);