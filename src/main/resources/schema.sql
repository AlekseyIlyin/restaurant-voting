DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS menu;
DROP TABLE IF EXISTS dishes;
DROP TABLE IF EXISTS voting;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users (
                    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
                    name        VARCHAR(128)                      NOT NULL ,
                    password    VARCHAR(128)                      NOT NULL ,
                    role        VARCHAR(32)                       NOT NULL ,
                    enabled     BOOL                DEFAULT TRUE  NOT NULL
);
CREATE UNIQUE INDEX users_unique_name_idx ON users (name);

CREATE TABLE restaurants (
                    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
                    name        VARCHAR(128)                      NOT NULL
);
CREATE UNIQUE INDEX restaurants_unique_name_idx ON restaurants (name);

CREATE TABLE menu (
                    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
                    rest_id     INTEGER                     NOT NULL ,
                    date        TIMESTAMP                   NOT NULL
);
CREATE UNIQUE INDEX menu_unique_restaurant_date_idx ON menu (rest_id,date);

CREATE TABLE dishes (
                    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
                    menu_id     INTEGER                     NOT NULL ,
                    name        VARCHAR(128)                NOT NULL ,
                    price       FLOAT
);
CREATE UNIQUE INDEX dishes_unique_menuid_name_idx ON dishes (menu_id,name);

CREATE TABLE voting (
                    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
                    user_id     INTEGER                     NOT NULL ,
                    rest_id     INTEGER                     NOT NULL ,
                    voting      TIMESTAMP   DEFAULT now()   NOT NULL
);
