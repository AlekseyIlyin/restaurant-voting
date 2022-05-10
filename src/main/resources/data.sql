DELETE FROM user_roles;
DELETE FROM voting;
DELETE FROM dishes;
DELETE FROM menu;
DELETE FROM restaurants;
DELETE FROM users;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001),
       ('USER', 100001);

INSERT INTO restaurants (name)
VALUES
    ('Astoria'),
    ('Kosmos'),
    ('Russian food (Русская кухня)');

INSERT INTO menu (rest_id)
VALUES
    (100003),
    (100004);

INSERT INTO dishes (menu_id, name, price)
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

INSERT INTO voting (user_id, rest_id, vote_datetime)
VALUES
    (100001, 100004, CURRENT_DATE),
    (100000, 100003, CURRENT_DATE + INTERVAL '3 HOUR'),
    (100001, 100004, CURRENT_DATE + INTERVAL '4 HOUR')
;