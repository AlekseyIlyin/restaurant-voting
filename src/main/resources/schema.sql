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
    id               INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    name             VARCHAR(255)                      NOT NULL,
    email            VARCHAR(255)                      NOT NULL,
    password         VARCHAR(255)                      NOT NULL,
    registered       TIMESTAMP           DEFAULT now() NOT NULL,
    enabled          BOOLEAN             DEFAULT TRUE  NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR(255)  NOT NULL,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurants (
                    id          INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
                    name        VARCHAR(128)                      NOT NULL
);
CREATE UNIQUE INDEX restaurants_unique_name_idx ON restaurants (name);

CREATE TABLE menu (
                    id          INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
                    rest_id     INTEGER                     NOT NULL ,
                    date        TIMESTAMP     DEFAULT CURRENT_DATE NOT NULL
);
CREATE UNIQUE INDEX menu_unique_restaurant_date_idx ON menu (rest_id,date);

CREATE TABLE dishes (
                    id          INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
                    menu_id     INTEGER                     NOT NULL ,
                    name        VARCHAR(128)                NOT NULL ,
                    price       FLOAT
);
CREATE UNIQUE INDEX dishes_unique_menuid_name_idx ON dishes (menu_id,name);

CREATE TABLE voting (
                    id          INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
                    user_id     INTEGER                     NOT NULL ,
                    rest_id     INTEGER                     NOT NULL ,
                    vote_datetime TIMESTAMP  DEFAULT CURRENT_DATE NOT NULL
);
CREATE UNIQUE INDEX voting_unique_userid_votedatetime_idx ON voting (user_id,vote_datetime);

CREATE FUNCTION getVotingResult(startDateTime TIMESTAMP, beforeDateTime TIMESTAMP)
    RETURNS TABLE (rest_id INTEGER, rate INTEGER)
    READS SQL DATA
        RETURN TABLE (
        SELECT rest_id, COUNT(rest_id) AS rate FROM voting WHERE vote_datetime IN (
            SELECT vdt.vd FROM (SELECT user_id, MAX(vote_datetime) AS vd FROM voting WHERE vote_datetime>=startDateTime AND vote_datetime < beforeDateTime GROUP BY user_id) AS vdt) GROUP BY rest_id);
