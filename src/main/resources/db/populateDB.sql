DELETE FROM numbers;
DELETE FROM errors;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO numbers (number, request, description, latency)
VALUES ('10', '1', '10 is the highest score possible in Olympics gymnastics competitions.', 378),
       ('11', '1', '11 is the miles per hours that the fastest moving land snake, the Black Mamba, can move.', 261),
       ('15', '2', '15 is the approximate speed in miles per hour a penguin swims at.', 133),
       ('5', '1', '5 is the number of dots in a quincunx.', 131),
       ('8', '1', '8 is the number of bits in a byte.', 135),
       ('3', '1', '3 is the number of sets needed to be won to win the whole match in volleyball.', 131),
       ('4', '1', '4 is the number of legs most furniture has.', 262),
       ('400', '1', '400 is the number of years in a period of the Gregorian calendar, of which 97 are leap years and 303 are common.', 132),
       ('600', '4', '600 is the advertised number of miles that NASCAR runs in the Coca-Cola 600, the longest race on any of the NASCAR circuits.', 131);

INSERT INTO errors (error) VALUES (DEFAULT);