CREATE TABLE data
(
    id    SERIAL PRIMARY KEY,
    value TEXT
);

INSERT INTO data (value)
SELECT CONCAT('Value ', n) AS value
FROM generate_series(1, 1000) AS n;