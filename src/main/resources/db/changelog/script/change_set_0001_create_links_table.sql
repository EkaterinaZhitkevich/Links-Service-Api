--liquibase formatted sql

--changeset Ekaterina.Zh:1

create table if not exists links(
    id bigserial primary key,
    long_link varchar(100) not null , -- unique???
    short_link varchar(10) not null , --add constraint unique
    data_time timestamp with time zone -- rename this column
);