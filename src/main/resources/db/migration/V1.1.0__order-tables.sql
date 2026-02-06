create table "order"
(
    id uuid not null,
    order_number varchar(255),
    customer_name varchar(255),
    customer_email varchar(255),
    customer_iban varchar(255),
    address_street varchar(255),
    address_zipcode varchar(255),
    address_city varchar(255),
    address_country varchar(255),
    address_region varchar(255),
    requested_delivery_date date,
    primary key (id)
);

create table order_position
(
    id bigint not null,
    order_id uuid references "order" (id) not null,
    rate numeric(38,4),
    quantity int not null,
    price_amount numeric(38,4),
    price_currency varchar(255),
    article_id varchar(13) references article (id) not null -- should we express this as a foreign key as this references a different bounded context?
)


