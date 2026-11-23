package sql;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
        String insert ="insert into client (full_name,email,registered)\n" +
                "values('client_1','client_1@mail.ru','25-01-10'),\n" +
                "('client_2','client_2@mail.ru','22-02-20'),\n" +
                "('client_3','client_3@mail.ru','22-02-15'),\n" +
                "('client_4','client_4@mail.ru','22-04-01'),\n" +
                "('client_5','client_5@mail.ru','22-05-05');";

        Files.writeString(fileClient,insert, StandardOpenOption.CREATE,StandardOpenOption.APPEND);
    }
    public void insertAccount() throws IOException {
        String insert = "insert into account_number (\n" +
                "client_id, account_number,balance,currency)\n" +
                "values \n" +
                "(1,'RU0000000001',15000,'RUB'),\n" +
                "(1,'RU0000000002',700,'RUB'),\n" +
                "(2,'RU0000000003',12000,'RUB'),\n" +
                "(3,'RU0000000004',57000,'RUB'),\n" +
                "(4,'RU0000000005',18000,'RUB'),\n" +
                "(5,'RU0000000006',22000,'RUB');\n";

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
