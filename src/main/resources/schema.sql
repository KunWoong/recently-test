create sequence taxi_seq start with 1 increment by 1
create sequence user_seq start with 1 increment by 1
create table taxi_allocation (id integer not null, accepted_at timestamp, address varchar(255), created_at timestamp, driver_id integer not null, passenger_id integer not null, status varchar(255), updated_at timestamp, version bigint not null, primary key (id))
create table user (id integer not null, created_at timestamp, email varchar(255), password varchar(255), updated_at timestamp, user_type varchar(255), primary key (id))