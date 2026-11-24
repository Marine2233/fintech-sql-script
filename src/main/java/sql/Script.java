package sql;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public final class Script {

    private final Path directory = Path.of("script");
    private final Path makeDB = directory.resolve("step1.sql");
    private final Path fileClient = directory.resolve("client.sql");
    private final Path fileAccount = directory.resolve("account.sql");


    public void createDB() throws IOException {
        String db = "create database fintech_training;\n";
        Files.createDirectories(directory);
        Files.writeString(makeDB,db, StandardOpenOption.CREATE,StandardOpenOption.APPEND);
    }

    public void queryAccount() throws IOException {
        String query = "select account_number from account_number\n" +
                "where client_id = 1;\n" +
                "\n" +
                "select account_number from account_number\n" +
                "where balance > 10000;\n" +
                "\n" +
                "select account_number from account_number\n" +
                "where balance = 0;\n" +
                "\n" +
                "select account_number, balance  desc from account_number\n" +
                "order by balance ;\n" +
                "\n" +
                "select account_number,balance  from account_number\n" +
                "order  by balance desc\n" +
                "limit (3);\n" +
                "\n" +
                "select account_number,balance  from account_number\n" +
                "where balance  between 15000 and 30000\n" +
                "order  by balance desc;\n";

        Files.writeString(fileAccount,query, StandardOpenOption.CREATE,StandardOpenOption.APPEND);
    }

    public void queryClient() throws IOException {
        String query ="\n" +
                "select * from client;\n" +
                "select full_name, email from client;\n" +
                "select * from client\n" +
                "where client_id = 3;\n" +
                "\n" +
                "select full_name from client \n" +
                "where registered > '2010-01-10';\n" +
                "\n" +
                "select full_name from client \n" +
                "where full_name ilike('%1');\n" +
                "\n";

        Files.writeString(fileClient,query, StandardOpenOption.CREATE,StandardOpenOption.APPEND);
    }

    public void insertClient() throws IOException {
        String insert ="INSERT INTO client (full_name, email, registered) VALUES\n" +
                "('Иванов Александр Сергеевич', 'a.ivanov@example.ru', '2025-01-15'),\n" +
                "('Смирнова Елена Викторовна', 'elena.smirnova@mail.ru', '2025-02-03'),\n" +
                "('Кузнецов Дмитрий Андреевич', 'd.kuznetsov@yandex.ru', '2025-03-19'),\n" +
                "('Попова Анна Михайловна', 'anna.popova@gmail.com', '2025-04-10'),\n" +
                "('Васильев Максим Олегович', 'm.vasiliev@bk.ru', '2025-05-22'),\n" +
                "('Петрова Ольга Николаевна', 'olga.petrova@inbox.ru', '2025-06-30'),\n" +
                "('Соколов Артём Владимирович', 'artem.sokolov@list.ru', '2025-08-14'),\n" +
                "('Михайлова Татьяна Павловна', 't.mikhailova@rambler.ru', '2025-09-05'),\n" +
                "('Новиков Сергей Игоревич', 's.novikov@example.com', '2025-11-11'),\n" +
                "('Фёдорова Наталья Алексеевна', 'n.fedorova@yandex.ru', '2026-01-20'),\n" +
                "('Морозов Алексей Евгеньевич', 'alex.morozov@gmail.com', '2026-03-08'),\n" +
                "('Волкова Екатерина Дмитриевна', 'e.volkova@mail.ru', '2026-05-17'),\n" +
                "('Алексеев Андрей Юрьевич', 'andrey.alekseev@example.ru', '2026-07-29'),\n" +
                "('Лебедева Мария Александровна', 'm.lebedeva@yandex.ru', '2026-10-02'),\n" +
                "('Семёнов Илья Романович', 'ilya.semenov@gmail.com', DEFAULT);\n";

        Files.writeString(fileClient,insert, StandardOpenOption.CREATE,StandardOpenOption.APPEND);
    }
    public void insertAccount() throws IOException {
        String insert = "INSERT INTO account_number (client_id, account_number, balance, currency) VALUES\n" +
                "(1, '40817810000000000001', 15400.50, 'RUB'),\n" +
                "(1, '40817810000000000002', 64200.00, 'RUB'),\n" +
                "(2, '40817810000000000003', 8950.75, 'RUB'),\n" +
                "(3, '40817810000000000004', 42000.00, 'RUB'),\n" +
                "(3, '40817810000000000005', 3100.20, 'RUB'),\n" +
                "(3, '40817810000000000006', 78500.00, 'RUB'),\n" +
                "(4, '40817810000000000007', 2500.00, 'RUB'),\n" +
                "(5, '40817810000000000008', 51340.90, 'RUB'),\n" +
                "(6, '40817810000000000009', 19800.00, 'RUB'),\n" +
                "(6, '40817810000000000010', 33450.15, 'RUB'),\n" +
                "(7, '40817810000000000011', 80000.00, 'RUB'),\n" +
                "(8, '40817810000000000012', 12600.40, 'RUB'),\n" +
                "(9, '40817810000000000013', 47900.00, 'RUB'),\n" +
                "(9, '40817810000000000014', 6200.80, 'RUB'),\n" +
                "(10, '40817810000000000015', 29100.00, 'RUB'),\n" +
                "(11, '40817810000000000016', 71450.60, 'RUB'),\n" +
                "(12, '40817810000000000017', 14200.00, 'RUB'),\n" +
                "(12, '40817810000000000018', 55000.25, 'RUB'),\n" +
                "(13, '40817810000000000019', 9750.00, 'RUB'),\n" +
                "(14, '40817810000000000020', 38600.30, 'RUB'),\n" +
                "(15, '40817810000000000021', 22300.00, 'RUB'),\n" +
                "(15, '40817810000000000022', 68900.45, DEFAULT),\n" +
                " (1, 'RU000000000000000001', 15000.00, 'RUB'),\n " +
                "(2, 'RU000000000000000002', 23500.50, 'RUB'),\n" +
                "(3, 'RU000000000000000005', 41000.00, 'RUB'),\n" +
                "(4, 'RU000000000000000009', 9800.75, 'RUB'),\n" +
                "(5, 'RU000000000000000007', 32000.00, 'RUB');\n";

        Files.writeString(fileAccount,insert, StandardOpenOption.CREATE,StandardOpenOption.APPEND);
    }


    public void createAccountTable() throws IOException {
        String table = "create table account_number(\n" +
                "account_id bigserial primary key,\n" +
                "client_id bigint not null references client(client_id),\n" +
                "account_number VARCHAR(20) not null unique,\n" +
                "balance NUMERIC(15,2) not null default 0.00\n" +
                "check (balance >= 0),\n" +
                "currency CHAR(3) not null default 'RUB'\n" +
                "CHECK (currency = 'RUB')\n" +
                ");";
        Files.writeString(fileAccount,table, StandardOpenOption.CREATE,StandardOpenOption.APPEND);
    }

    public void createTableClient(){
        String script = "create table client(\n" +
                "client_id bigserial primary key ,\n" +
                "full_name varchar(100) not null,\n" +
                "email varchar(50) not null unique,\n" +
                "registered date not null default current_date);";

        try {
            Files.writeString(fileClient,script, StandardOpenOption.CREATE,StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
