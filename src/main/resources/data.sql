DELETE FROM user_roles;
DELETE FROM vote;
DELETE FROM dish;
DELETE FROM menu;
DELETE FROM restaurant;
DELETE FROM users;

ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001),
       ('USER', 100001);

INSERT INTO restaurant (name)
VALUES
    ('Astoria'),
    ('Kosmos'),
    ('Russian food (Русская кухня)');

INSERT INTO menu (rest_id)
VALUES
    (100002),
    (100003),
    (100004);

INSERT INTO dish (menu_id, name, price)
VALUES
    (100005, 'ice cream', 3.99),
    (100005, 'Cinnamon waffle', 4.99),
    (100005, 'Fruit trifle',  3.44),
    (100006, 'Garlic Bread', 3.50),
    (100006, 'Mozzarella salad', 4.50),
    (100006, 'Olives', 3.99),
    (100007, 'Жульен', 5.59),
    (100007, 'Курник', 3.99),
    (100007, 'Растягай', 2.11)
;

INSERT INTO vote (user_id, rest_id)
VALUES
    (100001, 100002),
    (100000, 100003),
    (100001, 100004),
    (100001, 100004)
;