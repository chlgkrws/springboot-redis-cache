CREATE
DATABASE IF NOT EXISTS example;
USE example;

CREATE TABLE item
(
    id      BIGINT PRIMARY KEY,
    name    VARCHAR(30),
    price   double
);
