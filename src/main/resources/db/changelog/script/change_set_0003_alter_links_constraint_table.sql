--liquibase formatted sql

--changeset Ekaterina.Zh:3

alter table links add constraint short_link_check_length check ( char_length(short_link) > 2 );
alter table links add constraint long_link_check_length check ( char_length(long_link) >= 4 );