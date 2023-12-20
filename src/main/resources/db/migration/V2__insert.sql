INSERT INTO PUBLIC.PAGES (ID, NAME, URL, PARSING_X_PATH) VALUES (NEXT VALUE FOR sq_pages_id, 'Курс юаня', 'https://www.cbr.ru', '//div[@class=''main-indicator_rate''][2]/div[contains(@class,''mono-num'')][2]/text()');

--INSERT INTO PUBLIC.PARSING_RESULTS (ID, PARSING_DATE_TIME, RESULT, PAGE_ID, SENT) VALUES (NEXT VALUE FOR sq_parsing_results_id, '2020-10-16 21:54:55.000000', 'res1', 1, true);

INSERT INTO PUBLIC.SUBSCRIPTIONS (ID, USER_ID, PAGE_ID) VALUES (NEXT VALUE FOR sq_subscriptions_id, 'admin', 1);

INSERT INTO PUBLIC.USERS (LOGIN, PASSWORD, FIRST_NAME, LAST_NAME, TELEGRAM_ID) VALUES ('admin', 'admin', 'Ася', 'Мусатова', 432689145);


insert into PUBLIC.user_roles (id, user_id, role) values (NEXT VALUE FOR sq_user_roles_id, 'admin', 'ADMIN'),
                                                (NEXT VALUE FOR sq_user_roles_id, 'admin', 'USER');

