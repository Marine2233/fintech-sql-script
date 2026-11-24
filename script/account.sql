create table account_number(
account_id bigserial primary key,
client_id bigint not null references client(client_id),
account_number VARCHAR(20) not null unique,
balance NUMERIC(15,2) not null default 0.00
check (balance >= 0),
currency CHAR(3) not null default 'RUB'
CHECK (currency = 'RUB')
);

INSERT INTO account_number (client_id, account_number, balance, currency) VALUES
(1, '40817810000000000001', 15400.50, 'RUB'),
(1, '40817810000000000002', 64200.00, 'RUB'),
(2, '40817810000000000003', 8950.75, 'RUB'),
(3, '40817810000000000004', 42000.00, 'RUB'),
(3, '40817810000000000005', 3100.20, 'RUB'),
(3, '40817810000000000006', 78500.00, 'RUB'),
(4, '40817810000000000007', 2500.00, 'RUB'),
(5, '40817810000000000008', 51340.90, 'RUB'),
(6, '40817810000000000009', 19800.00, 'RUB'),
(6, '40817810000000000010', 33450.15, 'RUB'),
(7, '40817810000000000011', 80000.00, 'RUB'),
(8, '40817810000000000012', 12600.40, 'RUB'),
(9, '40817810000000000013', 47900.00, 'RUB'),
(9, '40817810000000000014', 6200.80, 'RUB'),
(10, '40817810000000000015', 29100.00, 'RUB'),
(11, '40817810000000000016', 71450.60, 'RUB'),
(12, '40817810000000000017', 14200.00, 'RUB'),
(12, '40817810000000000018', 55000.25, 'RUB'),
(13, '40817810000000000019', 9750.00, 'RUB'),
(14, '40817810000000000020', 38600.30, 'RUB'),
(15, '40817810000000000021', 22300.00, 'RUB'),
(15, '40817810000000000022', 68900.45, DEFAULT),
(1, 'RU000000000000000001', 15000.00, 'RUB'),
(2, 'RU000000000000000002', 23500.50, 'RUB'),
(3, 'RU000000000000000005', 41000.00, 'RUB'),
(4, 'RU000000000000000009', 9800.75, 'RUB'),
(5, 'RU000000000000000007', 32000.00, 'RUB');


select account_number from account_number
where client_id = 1;

select account_number from account_number
where balance > 10000;

select account_number from account_number
where balance = 0;

select account_number, balance  from account_number
order by balance desc;

select account_number,balance  from account_number
order  by balance desc
limit (3);

select account_number,balance  from account_number
where balance  between 2500 and 30000
order  by balance desc;
