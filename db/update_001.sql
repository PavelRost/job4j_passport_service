create table if not exists passport (
    id serial primary key not null,
    name varchar(50),
    serial int unique,
    number int unique,
    validity_Period TIMESTAMP
);


insert into passport(name, serial, number, validity_Period) values('Fedor', 2222, 222222, '2022-04-15');
insert into passport(name, serial, number, validity_Period) values('Gleb', 3333, 333333, '2022-10-01');
insert into passport(name, serial, number, validity_Period) values('Alisa', 4444, 444444, '2022-01-10');
