CREATE TABLE accident_type (
    id serial primary key not null,
    name varchar(2000)
);

CREATE TABLE rules (
    id serial primary key not null,
    name varchar(2000)
);

CREATE TABLE accident (
    id serial primary key not null,
    name varchar(2000),
    text varchar(2000),
    address varchar(2000),
    accident_type_id bigint not null references accident_type(id)
);

CREATE TABLE accident_rules
(
    accident_id bigint not null references accident(id),
    rules_id bigint not null references rules(id),
    primary key (accident_id, rules_id)
);

insert into accident_type (name) values ('Две машины'),('Машина и человек'),('Машина и велосипед');
insert into rules (name) values ('Статья. 1'),('Статья. 2'),('Статья. 3');
insert into accident (name, text, address, accident_type_id) values ('Иван','текст1','адрес1', 1),
                                                                    ('Дмитрий','текст2','адрес2', 2),
                                                                    ('Владимир','текст3','адрес3', 3);
insert into accident_rules (accident_id, rules_id) values (1, 1),(1, 2),
                                                          (2, 1),
                                                          (3, 2),(3, 3);
