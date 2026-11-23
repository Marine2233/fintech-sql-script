create table client(
client_id bigserial primary key ,
full_name varchar(100) not null,
email varchar(50) not null unique,
registered date not null default current_date);insert into client (full_name,email,registered)
values('client_1','client_1@mail.ru','25-01-10'),
('client_2','client_2@mail.ru','22-02-20'),
('client_3','client_3@mail.ru','22-02-15'),
('client_4','client_4@mail.ru','22-04-01'),
('client_5','client_5@mail.ru','22-05-05');
select * from client;
select full_name, email from client;
select * from client
where client_id = 3;

select full_name from client 
where registered > '2010-01-10';

select full_name from client 
where full_name ilike('%1');

