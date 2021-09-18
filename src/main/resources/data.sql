create table if not exists "user"
(
    id serial not null
        constraint user_pk
            primary key,
    login varchar(30) not null,
    password varchar(100) not null,
    name varchar(30) not null,
    number varchar(15) not null,
    mail varchar(30) not null
);

create table if not exists "token"
(
    id serial not null
        constraint token_pk
            primary key,
    token varchar(100) not null,
    user_id int not null
);
