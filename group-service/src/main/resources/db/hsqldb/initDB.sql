DROP TABLE groups_users IF EXISTS;
DROP TABLE groups IF EXISTS;
DROP TABLE users_messengers IF EXISTS;
DROP TABLE users IF EXISTS;

CREATE TABLE users (
    id       INTEGER IDENTITY PRIMARY KEY,
    nickname VARCHAR(30)
);
CREATE UNIQUE INDEX users_nickname ON users (nickname);

CREATE TABLE users_messengers (
    user_id     INTEGER NOT NULL,
    service_type VARCHAR(10) NOT NULL,
    token        VARCHAR(255) NOT NULL,
    target       VARCHAR(255) NOT NULL,
    PRIMARY KEY (user_id, service_type)
);
ALTER TABLE users_messengers
    ADD CONSTRAINT users_messengers_users FOREIGN KEY (user_id)
        REFERENCES users (id);

CREATE TABLE groups (
    id        INTEGER IDENTITY PRIMARY KEY,
    groupname VARCHAR(30)
    );
CREATE UNIQUE INDEX groups_groupname ON groups (groupname);

CREATE TABLE groups_users (
    group_id INTEGER NOT NULL,
    user_id  INTEGER NOT NULL,
    PRIMARY KEY (group_id, user_id)
);
ALTER TABLE groups_users
    ADD CONSTRAINT groups_users_groups FOREIGN KEY (group_id)
        REFERENCES groups (id);
ALTER TABLE groups_users
    ADD CONSTRAINT groups_users_users FOREIGN KEY (user_id)
        REFERENCES users (id);
