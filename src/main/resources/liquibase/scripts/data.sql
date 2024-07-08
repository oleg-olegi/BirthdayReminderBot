-- liquibase formatted sql

-- changeset oshinkevich:1
CREATE TABLE birthdayDB
(
    id            BIGSERIAL PRIMARY KEY,
    chat_id       BIGSERIAL UNIQUE NOT NULL,
    full_name     TEXT             NOT NULL,
    date_of_birth DATE             NOT NULL
);

