DROP TABLE IF EXISTS client;

CREATE TABLE client (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  email VARCHAR(250) DEFAULT NULL
);

INSERT INTO client(name, email) VALUES
  ('Lokesh', 'howtodoinjava@gmail.com'),
  ('Alex', 'abc@gmail.com'),
  ('David', 'titanic@gmail.com');