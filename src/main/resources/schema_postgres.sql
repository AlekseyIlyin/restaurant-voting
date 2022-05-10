DROP FUNCTION IF EXISTS getVotingResult;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS menu;
DROP TABLE IF EXISTS dishes;
DROP TABLE IF EXISTS voting;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name             VARCHAR                           NOT NULL,
    email            VARCHAR                           NOT NULL,
    password         VARCHAR                           NOT NULL,
    registered       TIMESTAMP           DEFAULT now() NOT NULL,
    enabled          BOOL                DEFAULT TRUE  NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR NOT NULL,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurants (
                    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
                    name        VARCHAR(128)                      NOT NULL
);
CREATE UNIQUE INDEX restaurants_unique_name_idx ON restaurants (name);

CREATE TABLE menu (
                    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
                    rest_id     INTEGER                     NOT NULL ,
                    date        TIMESTAMP     DEFAULT CURRENT_DATE NOT NULL
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
                    vote_datetime TIMESTAMP  DEFAULT CURRENT_DATE NOT NULL
);
CREATE UNIQUE INDEX voting_unique_userid_votedatetime_idx ON voting (user_id,vote_datetime);

CREATE FUNCTION getVotingResult(startDateTime TIMESTAMP, beforeDateTime TIMESTAMP)
    RETURNS TABLE (rest_id INTEGER, rate INTEGER)
    LANGUAGE sql
    AS '
        SELECT rest_id, COUNT(rest_id) AS rate FROM voting WHERE vote_datetime IN (
            SELECT vdt.vd FROM (SELECT user_id, MAX(vote_datetime) AS vd FROM voting WHERE vote_datetime>=startDateTime AND vote_datetime < beforeDateTime GROUP BY user_id) AS vdt) GROUP BY rest_id;
';