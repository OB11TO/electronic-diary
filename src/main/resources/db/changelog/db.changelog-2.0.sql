--liquibase formatted sql

--changeset obito:1
CREATE TABLE IF NOT EXISTS users
(
    id         BIGSERIAL PRIMARY KEY,
    username   VARCHAR(64) NOT NULL UNIQUE,
    password   VARCHAR(128) DEFAULT '{bcrypt}123',
    firstname  VARCHAR(64),
    lastname   VARCHAR(64),
    role       VARCHAR(32)
);
