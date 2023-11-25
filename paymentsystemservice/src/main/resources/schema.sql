CREATE TABLE IF NOT EXISTS transaction_tb (
    id SERIAL primary key,
    type int,
    transaction_date date,
    transaction_value decimal,
    cpf bigint,
    card varchar(255),
    transaction_time time,
    owner_store varchar(255),
    name_store varchar(255)
);