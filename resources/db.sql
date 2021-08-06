CREATE TABLE IF NOT EXISTS accounts (
   id SERIAL PRIMARY KEY,
   holder VARCHAR(50) NOT NULL,
   amount INT NOT NULL);

DO
$do$
BEGIN
IF NOT EXISTS (SELECT * FROM accounts) THEN
      INSERT INTO accounts
      (holder, amount)
      VALUES
              ('Иванов Иван', 500),
              ('Валентинов Валентин', 600),
              ('Константинов Константин', 700),
              ('Михайлов Михаил', 800),
              ('Гай Игоорь', 900),
              ('Носов Николай', 1000),
              ('Николаев Константин', 10100),
              ('Михайлов Алексей', 2300),
              ('Константинов Шон', 15005),
              ('Бабушкин Михаил', 0);
END IF;
END
$do$;
