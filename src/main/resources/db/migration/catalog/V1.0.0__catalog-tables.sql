create table size (
    id  varchar(32) not null,
    name varchar(64),
    surcharge numeric(38,4),

    primary key (id)
);

create table rebate (
    id varchar(13) not null,
    name varchar(64),
    rebate numeric(38,4),

    primary key (id)
);

create table article (
    id varchar(13) not null,
    name varchar(64),
    number_of_decimal_digits int,

    price_amount numeric(38,4),
    price_currency varchar(255),

    size_id varchar(32) references size(id) not null,
    rebate_id varchar(13) references rebate(id) not null,

    primary key (id)
);

