DELETE FROM voting;
DELETE FROM dishes;
DELETE FROM menu;
DELETE FROM restaurants;
DELETE FROM users;

INSERT INTO users (id, name, password, role)
VALUES
    (100001, 'Admin', '1', 'ADMIN'),
    (100002, 'User1', '1', 'USER'),
    (100003, 'User2', '1', 'USER');

INSERT INTO restaurants (id, name)
VALUES
    (100001, 'Astoria'),
    (100002, 'Kosmos');

INSERT INTO menu (id, rest_id, date)
VALUES
    (100001, 100001, '2022-04-15'),
    (100002, 100002, '2022-04-15');

INSERT INTO dishes (id, menu_id, name, price)
VALUES
    (100001, 100001, 'fish', 12.2),
    (100002, 100001, 'mean', 10.0),
    (100003, 100001, 'tea',  1.0),
    (100004, 100002, 'ear fish', 5.1),
    (100005, 100002, 'hot dog', 2.1);

INSERT INTO voting (id, user_id, rest_id, voting)
VALUES
    (100001, 100002, 100001, '2022-04-15 10:00:01'),
    (100002, 100002, 100001, '2022-04-15 11:00:00'),
    (100003, 100003, 100001, '2022-04-15 09:00:00');