--liquibase formatted sql

--changeset Ekaterina.Zh:2

alter table  links add constraint short_link_unique unique (short_link);
alter table links rename data_time to date_time;
