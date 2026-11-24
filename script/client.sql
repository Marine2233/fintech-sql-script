create table client(
client_id bigserial primary key ,
full_name varchar(100) not null,
email varchar(50) not null unique,
registered date not null default current_date);

INSERT INTO client (full_name, email, registered) VALUES
('Иванов Александр Сергеевич', 'a.ivanov@example.ru', '2025-01-15'),
('Смирнова Елена Викторовна', 'elena.smirnova@mail.ru', '2025-02-03'),
('Кузнецов Дмитрий Андреевич', 'd.kuznetsov@yandex.ru', '2025-03-19'),
('Попова Анна Михайловна', 'anna.popova@gmail.com', '2025-04-10'),
('Васильев Максим Олегович', 'm.vasiliev@bk.ru', '2025-05-22'),
('Петрова Ольга Николаевна', 'olga.petrova@inbox.ru', '2025-06-30'),
('Соколов Артём Владимирович', 'artem.sokolov@list.ru', '2025-08-14'),
('Михайлова Татьяна Павловна', 't.mikhailova@rambler.ru', '2025-09-05'),
('Новиков Сергей Игоревич', 's.novikov@example.com', '2025-11-11'),
('Фёдорова Наталья Алексеевна', 'n.fedorova@yandex.ru', '2026-01-20'),
('Морозов Алексей Евгеньевич', 'alex.morozov@gmail.com', '2026-03-08'),
('Волкова Екатерина Дмитриевна', 'e.volkova@mail.ru', '2026-05-17'),
('Алексеев Андрей Юрьевич', 'andrey.alekseev@example.ru', '2026-07-29'),
('Лебедева Мария Александровна', 'm.lebedeva@yandex.ru', '2026-10-02'),
('Семёнов Илья Романович', 'ilya.semenov@gmail.com', DEFAULT);


select * from client;
select full_name, email from client;
select * from client
where client_id = 3;

select full_name from client 
where registered > '2010-01-10';

select full_name from client 
where full_name ilike('%1');

