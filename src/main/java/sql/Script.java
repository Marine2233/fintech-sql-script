package sql;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public final class Script {

    private final Path DIRECTORY = Path.of("script");
    private final Path MAKE_DB = DIRECTORY.resolve("step1.sql");
    private final Path FILE_CLIENT = DIRECTORY.resolve("client.sql");
    private final Path FILE_ACCOUNT = DIRECTORY.resolve("account.sql");
    private final Path SQL_BASE_SCRIPT_FILE = DIRECTORY.resolve("05_advanced_select.sql");


    public void createDB() throws IOException {
        String db = "create database fintech_training;\n";
        Files.createDirectories(DIRECTORY);
        Files.writeString(MAKE_DB,db, StandardOpenOption.CREATE,StandardOpenOption.APPEND);
    }

    public void queryForFileSqlBaseScript() throws IOException {
        String line ="\n" +
                "-- =====================================================================\n" +
                "-- Задание 1. Выборка счетов повышенного риска\n" +
                "-- Условие: счёт принадлежит клиенту с ID 1, 2 или 3; баланс от 5000 до 30000\n" +
                "-- включительно; исключить баланс ровно 12000. Вывести client_id, account_number,\n" +
                "-- balance. Сортировка: по убыванию баланса, затем по возрастанию account_id.\n" +
                "-- =====================================================================\n" +
                "SELECT client_id, account_number, balance\n" +
                "FROM account_number\n" +
                "WHERE client_id IN (1, 2, 3)\n" +
                "  AND balance BETWEEN 5000 AND 30000\n" +
                "  AND NOT balance = 12000\n" +
                "ORDER BY balance DESC, account_id ASC;\n" +
                "\n" +
                "\n" +
                "-- =====================================================================\n" +
                "-- Задание 2. Исключения из финансового отчёта\n" +
                "-- Условие: клиент не имеет ID 2 или 5 И баланс < 1000 или > 20000.\n" +
                "-- Вывести номер счёта, ID клиента и баланс. Сортировка: по убыванию баланса.\n" +
                "-- =====================================================================\n" +
                "SELECT account_number, client_id, balance\n" +
                "FROM account_number\n" +
                "WHERE client_id NOT IN (2, 5)\n" +
                "  AND (balance < 1000 OR balance > 20000)\n" +
                "ORDER BY balance DESC;\n" +
                "\n" +
                "\n" +
                "-- =====================================================================\n" +
                "-- Задание 3. Поиск клиентов для персонального предложения\n" +
                "-- Условие: регистрация не раньше 2023-01-01; имя содержит «а» или «о»\n" +
                "-- без учёта регистра; клиенты с ID 2 и 4 исключаются.\n" +
                "-- Сортировка: по дате регистрации от новых к старым, затем по ID.\n" +
                "-- =====================================================================\n" +
                "SELECT client_id, full_name, email, registered\n" +
                "FROM client\n" +
                "WHERE registered >= '2023-01-01'\n" +
                "  AND (full_name ILIKE '%а%' OR full_name ILIKE '%о%')\n" +
                "  AND client_id NOT IN (2, 4)\n" +
                "ORDER BY registered DESC, client_id ASC;\n" +
                "\n" +
                "\n" +
                "-- =====================================================================\n" +
                "-- Задание 4. Страничный просмотр счетов\n" +
                "-- Условие: положительный баланс; сортировка по убыванию баланса, затем по\n" +
                "-- возрастанию account_id; размер страницы — 2 записи (страницы 1, 2 и 3).\n" +
                "-- =====================================================================\n" +
                "-- Первая страница\n" +
                "SELECT account_id, account_number, balance\n" +
                "FROM account_number\n" +
                "WHERE balance > 0\n" +
                "ORDER BY balance DESC, account_id ASC\n" +
                "LIMIT 2 OFFSET 0;\n" +
                "\n" +
                "-- Вторая страница\n" +
                "SELECT account_id, account_number, balance\n" +
                "FROM account_number\n" +
                "WHERE balance > 0\n" +
                "ORDER BY balance DESC, account_id ASC\n" +
                "LIMIT 2 OFFSET 2;\n" +
                "\n" +
                "-- Третья страница\n" +
                "SELECT account_id, account_number, balance\n" +
                "FROM account_number\n" +
                "WHERE balance > 0\n" +
                "ORDER BY balance DESC, account_id ASC\n" +
                "LIMIT 2 OFFSET 4;\n" +
                "\n" +
                "\n" +
                "-- =====================================================================\n" +
                "-- Задание 5. Проверка номеров банковских счетов\n" +
                "-- Условие: номера начинаются с RU, не заканчиваются на 2, принадлежат\n" +
                "-- клиентам с ID от 1 до 4 включительно. Сортировка по client_id и номеру.\n" +
                "-- =====================================================================\n" +
                "SELECT client_id, account_number, balance\n" +
                "FROM account_number\n" +
                "WHERE account_number LIKE 'RU%'\n" +
                "  AND account_number NOT LIKE '%2'\n" +
                "  AND client_id BETWEEN 1 AND 4\n" +
                "ORDER BY client_id ASC, account_number ASC;\n" +
                "\n" +
                "\n" +
                "-- =====================================================================\n" +
                "-- Задание 6. Поиск счетов с разными порогами для клиентов\n" +
                "-- Условие: (ID 1 и баланс > 10000) ИЛИ (ID 2 и баланс > 5000) ИЛИ\n" +
                "-- (ID 3 и баланс < 60000), но нулевой баланс исключён для всех.\n" +
                "-- =====================================================================\n" +
                "SELECT client_id, account_number, balance\n" +
                "FROM account_number\n" +
                "WHERE (\n" +
                "    (client_id = 1 AND balance > 10000)\n" +
                "    OR (client_id = 2 AND balance > 5000)\n" +
                "    OR (client_id = 3 AND balance < 60000)\n" +
                ")\n" +
                "AND balance != 0\n" +
                "ORDER BY client_id ASC, balance DESC;\n" +
                "\n" +
                "\n" +
                "-- =====================================================================\n" +
                "-- Задание 7. Поиск уникальных клиентов\n" +
                "-- Условие: уникальные ID клиентов со счетами от 10000 до 30000 включительно\n" +
                "-- или > 50000; исключить клиента с ID 5; сортировка по возрастанию ID.\n" +
                "-- =====================================================================\n" +
                "SELECT DISTINCT client_id\n" +
                "FROM account_number\n" +
                "WHERE (\n" +
                "    (balance BETWEEN 10000 AND 30000)\n" +
                "    OR (balance > 50000)\n" +
                ")\n" +
                "AND client_id != 5\n" +
                "ORDER BY client_id ASC;\n" +
                "\n" +
                "\n" +
                "-- =====================================================================\n" +
                "-- Задание 8. Формирование списка крупных счетов\n" +
                "-- Условие: баланс >= 10000; псевдонимы столбцов; сортировка по убыванию\n" +
                "-- баланса и возрастанию account_id; пропустить 1-ю запись, вывести 3.\n" +
                "-- =====================================================================\n" +
                "SELECT\n" +
                "    account_number AS bank_account,\n" +
                "    client_id AS customer_id,\n" +
                "    balance AS current_balance\n" +
                "FROM account_number\n" +
                "WHERE balance >= 10000\n" +
                "ORDER BY balance DESC, account_id ASC\n" +
                "LIMIT 3 OFFSET 1;\n" +
                "\n" +
                "\n" +
                "-- =====================================================================\n" +
                "-- Задание 9*. Проверка границ условий\n" +
                "-- Условие: два запроса для счетов вне диапазона [5000; 30000] включительно.\n" +
                "-- =====================================================================\n" +
                "-- Вариант 1: с операторами сравнения и OR\n" +
                "SELECT account_id, balance\n" +
                "FROM account_number\n" +
                "WHERE balance < 5000 OR balance > 30000\n" +
                "ORDER BY account_id ASC;\n" +
                "\n" +
                "-- Вариант 2: с отрицанием NOT BETWEEN\n" +
                "SELECT account_id, balance\n" +
                "FROM account_number\n" +
                "WHERE balance NOT BETWEEN 5000 AND 30000\n" +
                "ORDER BY account_id ASC;\n" +
                "\n" +
                "\n" +
                "-- =====================================================================\n" +
                "-- Задание 10*. Проверка логики скобок\n" +
                "-- Запрос А: счета клиентов 1 или 2 с балансом > 10000.\n" +
                "-- Запрос Б: все счета клиента 1 + счета клиента 2 с балансом > 10000.\n" +
                "-- =====================================================================\n" +
                "-- Запрос А\n" +
                "SELECT account_id, account_number, balance\n" +
                "FROM account_number\n" +
                "WHERE (client_id = 1 OR client_id = 2)\n" +
                "  AND balance > 10000\n" +
                "ORDER BY account_id ASC;\n" +
                "\n" +
                "-- Запрос Б\n" +
                "SELECT account_id, account_number, balance\n" +
                "FROM account_number\n" +
                "WHERE client_id = 1\n" +
                "   OR (client_id = 2 AND balance > 10000)\n" +
                "ORDER BY account_id ASC;";

        Files.writeString(SQL_BASE_SCRIPT_FILE,line,StandardOpenOption.CREATE,StandardOpenOption.APPEND);
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

        Files.writeString(FILE_ACCOUNT,query, StandardOpenOption.CREATE,StandardOpenOption.APPEND);
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

        Files.writeString(FILE_CLIENT,query, StandardOpenOption.CREATE,StandardOpenOption.APPEND);
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

        Files.writeString(FILE_CLIENT,insert, StandardOpenOption.CREATE,StandardOpenOption.APPEND);
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

        Files.writeString(FILE_ACCOUNT,insert, StandardOpenOption.CREATE,StandardOpenOption.APPEND);
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
        Files.writeString(FILE_ACCOUNT,table, StandardOpenOption.CREATE,StandardOpenOption.APPEND);
    }

    public void createTableClient(){
        String script = "create table client(\n" +
                "client_id bigserial primary key ,\n" +
                "full_name varchar(100) not null,\n" +
                "email varchar(50) not null unique,\n" +
                "registered date not null default current_date);";

        try {
            Files.writeString(FILE_CLIENT,script, StandardOpenOption.CREATE,StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
