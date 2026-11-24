
-- =====================================================================
-- Задание 1. Выборка счетов повышенного риска
-- Условие: счёт принадлежит клиенту с ID 1, 2 или 3; баланс от 5000 до 30000
-- включительно; исключить баланс ровно 12000. Вывести client_id, account_number,
-- balance. Сортировка: по убыванию баланса, затем по возрастанию account_id.
-- =====================================================================
SELECT client_id, account_number, balance
FROM account_number
WHERE client_id IN (1, 2, 3)
  AND balance BETWEEN 5000 AND 30000
  AND NOT balance = 12000
ORDER BY balance DESC, account_id ASC;


-- =====================================================================
-- Задание 2. Исключения из финансового отчёта
-- Условие: клиент не имеет ID 2 или 5 И баланс < 1000 или > 20000.
-- Вывести номер счёта, ID клиента и баланс. Сортировка: по убыванию баланса.
-- =====================================================================
SELECT account_number, client_id, balance
FROM account_number
WHERE client_id NOT IN (2, 5)
  AND (balance < 1000 OR balance > 20000)
ORDER BY balance DESC;


-- =====================================================================
-- Задание 3. Поиск клиентов для персонального предложения
-- Условие: регистрация не раньше 2023-01-01; имя содержит «а» или «о»
-- без учёта регистра; клиенты с ID 2 и 4 исключаются.
-- Сортировка: по дате регистрации от новых к старым, затем по ID.
-- =====================================================================
SELECT client_id, full_name, email, registered
FROM client
WHERE registered >= '2023-01-01'
  AND (full_name ILIKE '%а%' OR full_name ILIKE '%о%')
  AND client_id NOT IN (2, 4)
ORDER BY registered DESC, client_id ASC;


-- =====================================================================
-- Задание 4. Страничный просмотр счетов
-- Условие: положительный баланс; сортировка по убыванию баланса, затем по
-- возрастанию account_id; размер страницы — 2 записи (страницы 1, 2 и 3).
-- =====================================================================
-- Первая страница
SELECT account_id, account_number, balance
FROM account_number
WHERE balance > 0
ORDER BY balance DESC, account_id ASC
LIMIT 2 OFFSET 0;

-- Вторая страница
SELECT account_id, account_number, balance
FROM account_number
WHERE balance > 0
ORDER BY balance DESC, account_id ASC
LIMIT 2 OFFSET 2;

-- Третья страница
SELECT account_id, account_number, balance
FROM account_number
WHERE balance > 0
ORDER BY balance DESC, account_id ASC
LIMIT 2 OFFSET 4;


-- =====================================================================
-- Задание 5. Проверка номеров банковских счетов
-- Условие: номера начинаются с RU, не заканчиваются на 2, принадлежат
-- клиентам с ID от 1 до 4 включительно. Сортировка по client_id и номеру.
-- =====================================================================
SELECT client_id, account_number, balance
FROM account_number
WHERE account_number LIKE 'RU%'
  AND account_number NOT LIKE '%2'
  AND client_id BETWEEN 1 AND 4
ORDER BY client_id ASC, account_number ASC;


-- =====================================================================
-- Задание 6. Поиск счетов с разными порогами для клиентов
-- Условие: (ID 1 и баланс > 10000) ИЛИ (ID 2 и баланс > 5000) ИЛИ
-- (ID 3 и баланс < 60000), но нулевой баланс исключён для всех.
-- =====================================================================
SELECT client_id, account_number, balance
FROM account_number
WHERE (
    (client_id = 1 AND balance > 10000)
    OR (client_id = 2 AND balance > 5000)
    OR (client_id = 3 AND balance < 60000)
)
AND balance != 0
ORDER BY client_id ASC, balance DESC;


-- =====================================================================
-- Задание 7. Поиск уникальных клиентов
-- Условие: уникальные ID клиентов со счетами от 10000 до 30000 включительно
-- или > 50000; исключить клиента с ID 5; сортировка по возрастанию ID.
-- =====================================================================
SELECT DISTINCT client_id
FROM account_number
WHERE (
    (balance BETWEEN 10000 AND 30000)
    OR (balance > 50000)
)
AND client_id != 5
ORDER BY client_id ASC;


-- =====================================================================
-- Задание 8. Формирование списка крупных счетов
-- Условие: баланс >= 10000; псевдонимы столбцов; сортировка по убыванию
-- баланса и возрастанию account_id; пропустить 1-ю запись, вывести 3.
-- =====================================================================
SELECT
    account_number AS bank_account,
    client_id AS customer_id,
    balance AS current_balance
FROM account_number
WHERE balance >= 10000
ORDER BY balance DESC, account_id ASC
LIMIT 3 OFFSET 1;


-- =====================================================================
-- Задание 9*. Проверка границ условий
-- Условие: два запроса для счетов вне диапазона [5000; 30000] включительно.
-- =====================================================================
-- Вариант 1: с операторами сравнения и OR
SELECT account_id, balance
FROM account_number
WHERE balance < 5000 OR balance > 30000
ORDER BY account_id ASC;

-- Вариант 2: с отрицанием NOT BETWEEN
SELECT account_id, balance
FROM account_number
WHERE balance NOT BETWEEN 5000 AND 30000
ORDER BY account_id ASC;


-- =====================================================================
-- Задание 10*. Проверка логики скобок
-- Запрос А: счета клиентов 1 или 2 с балансом > 10000.
-- Запрос Б: все счета клиента 1 + счета клиента 2 с балансом > 10000.
-- =====================================================================
-- Запрос А
SELECT account_id, account_number, balance
FROM account_number
WHERE (client_id = 1 OR client_id = 2)
  AND balance > 10000
ORDER BY account_id ASC;

-- Запрос Б
SELECT account_id, account_number, balance
FROM account_number
WHERE client_id = 1
   OR (client_id = 2 AND balance > 10000)
ORDER BY account_id ASC;