create table USERS (
    id integer GENERATED ALWAYS AS IDENTITY primary key,
    name varchar,
    age  int4,
    salary float8
);

insert into USERS ( name,age,salary) values ('Vasia',34,1456.78);
insert into USERS ( name,age,salary) values ('Zenek',45,1891.88);
insert into USERS ( name,age,salary) values ('Boss',49,2479.54);
