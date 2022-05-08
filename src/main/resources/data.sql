DELETE FROM voting;
DELETE FROM dishes;
DELETE FROM menu;
DELETE FROM restaurants;
DELETE FROM users;

INSERT INTO users (name, password, role)
VALUES
    ('Admin', '1', 'ADMIN'),
    ('User1', '1', 'USER'),
    ('User2', '1', 'USER');

INSERT INTO restaurants (name)
VALUES
    ('Astoria'),
    ('Kosmos');

INSERT INTO menu (rest_id, date)
VALUES
    (100003, '2022-04-15'),
    (100004, '2022-04-15');

INSERT INTO dishes (menu_id, name, price)
VALUES
    (100005, 'fish', 12.2),
    (100005, 'mean', 10.0),
    (100005, 'tea',  1.0),
    (100006, 'ear fish', 5.1),
    (100006, 'hot dog', 2.1);

INSERT INTO voting (user_id, rest_id, voting)
VALUES
    (100001, 100004, '2022-04-15 10:00:01'),
    (100000, 100003, '2022-04-15 10:00:01'),
    (100001, 100004, '2022-04-15 10:59:59'),
    (100002, 100004, '2022-04-15 09:00:00');