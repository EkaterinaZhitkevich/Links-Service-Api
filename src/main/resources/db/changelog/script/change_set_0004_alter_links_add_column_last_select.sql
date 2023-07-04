--liquibase formatted sql

--changeset Ekaterina.Zh:4

alter table links add column last_select timestamp;
