CREATE TABLE users
(
    login       VARCHAR(50) PRIMARY KEY,
    password    VARCHAR(50)  NOT NULL,
    first_name  VARCHAR(250) NOT NULL,
    last_name   VARCHAR(250) NOT NULL,
    telegram_id BIGINT       NOT NULL
);

CREATE TABLE pages
(
    id             BIGINT PRIMARY KEY,
    name           VARCHAR(100),
    url            VARCHAR(2000) NOT NULL,
    parsing_x_path VARCHAR       NOT NULL
);

create sequence  sq_pages_id start with 1 increment by 1;

CREATE TABLE subscriptions
(
    id      BIGINT PRIMARY KEY,
    user_id VARCHAR(50),
    page_id BIGINT
);

create sequence  sq_subscriptions_id start with 1 increment by 1;

CREATE TABLE parsing_results
(
    id                BIGINT PRIMARY KEY,
    parsing_date_time TIMESTAMP,
    result            VARCHAR(4000),
    page_id           BIGINT,
    sent              BOOLEAN
);

create sequence  sq_parsing_results_id start with 1 increment by 1;

CREATE TABLE user_roles
(
    id                BIGINT PRIMARY KEY,
    user_id       VARCHAR(50) NOT NULL,
    role    VARCHAR(10)  NOT NULL
);

create sequence  sq_user_roles_id start with 1 increment by 1;