DROP TABLE IF EXISTS Person;

CREATE TABLE Person (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  age INT NOT NULL
);

INSERT INTO Person (first_name, last_name, age) VALUES
  ('Tim', 'Smith', 23),
  ('John', 'Rogers', 26),
  ('Caption', 'America', 40);