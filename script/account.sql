create table account_number(
account_id bigserial primary key,
client_id bigint not null references client(client_id),
account_number VARCHAR(20) not null unique,
balance NUMERIC(15,2) not null default 0.00
check (balance >= 0),
currency CHAR(3) not null default 'RUB'
CHECK (currency = 'RUB')
);insert into account_number (
client_id, account_number,balance,currency)
values 
(1,'RU0000000001',15000,'RUB'),
(1,'RU0000000002',700,'RUB'),
(2,'RU0000000003',12000,'RUB'),
(3,'RU0000000004',57000,'RUB'),
(4,'RU0000000005',18000,'RUB'),
(5,'RU0000000006',22000,'RUB');
select account_number from account_number
where client_id = 1;

select account_number from account_number
where balance > 10000;

select account_number from account_number
where balance = 0;

select account_number, balance  desc from account_number
order by balance ;

select account_number,balance  from account_number
order  by balance desc
limit (3);

select account_number,balance  from account_number
where balance  between 15000 and 30000
order  by balance desc;
