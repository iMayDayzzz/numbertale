DROP TABLE IF EXISTS numbers;
DROP TABLE IF EXISTS errors;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE numbers
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    number      INTEGER NOT NULL,
    request     INTEGER NOT NULL,
    description TEXT    NOT NULL,
    latency     INTEGER NOT NULL
);
CREATE UNIQUE INDEX number_idx ON numbers (number);

CREATE TABLE errors
(
    id    INTEGER PRIMARY KEY DEFAULT 500,
    error INTEGER             DEFAULT 0 NOT NULL
)