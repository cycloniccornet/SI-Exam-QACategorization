DROP TABLE IF EXISTS answers;

CREATE TABLE answers
(
    id     LONG          PRIMARY KEY,
    question   VARCHAR(50)   NOT NULL,
    answer   VARCHAR(50)   NOT NULL
);
