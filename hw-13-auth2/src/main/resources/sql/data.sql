insert into ROLES(name) values ('READER');
insert into ROLES(name) values ('EDITOR');

insert into USERS(user_name, password) values ('user', '$2a$10$bfhDL0Vbp13GO0IJAntFkuAK765RtGRHsG0IjrSlUBbsd5dUc9t9i');
insert into USERS(user_name, password) values ('admin', '$2a$10$bfhDL0Vbp13GO0IJAntFkuAK765RtGRHsG0IjrSlUBbsd5dUc9t9i');

insert into USER_ROLES(user_name, role_id) values ('user', (select id from ROLES where name = 'READER'));
insert into USER_ROLES(user_name, role_id) values ('admin', (select id from ROLES where name = 'READER'));
insert into USER_ROLES(user_name, role_id) values ('admin', (select id from ROLES where name = 'EDITOR'));

INSERT INTO BOOKS(isbn, title) VALUES (9785040941193, '100 лучших сказок всех времен и народов');
INSERT INTO BOOKS(isbn, title) VALUES (9785699120147, 'Колобок');
INSERT INTO BOOKS(isbn, title) VALUES (9785041060886, 'Вечеринка в Хэллоуин');
INSERT INTO BOOKS(isbn, title) VALUES (9785041172381, 'Рождество Эркюля Пуаро');
INSERT INTO BOOKS(isbn, title) VALUES (9785041567637, 'Слоны умеют помнить');

insert into GENRES(genre_name) values ('Сказки');
insert into GENRES(genre_name) values ('Детектив');

insert into AUTHORS(full_name) values ('Русские народные сказки');
insert into AUTHORS(full_name) values ('Якоб и Вильгельм Гримм');
insert into AUTHORS(full_name) values ('Шарль Перро');
insert into AUTHORS(full_name) values ('Джозеф Джекобс');
insert into AUTHORS(full_name) values ('Андерсен Ханс Кристиан');
insert into AUTHORS(full_name) values ('Агата Кристи');


insert into BOOK_GENRES(isbn, genre_id) values (9785699120147, (select id from GENRES where genre_name = 'Сказки'));
insert into BOOK_GENRES(isbn, genre_id) values (9785040941193, (select id from GENRES where genre_name = 'Сказки'));
insert into BOOK_GENRES(isbn, genre_id) values (9785041060886, (select id from GENRES where genre_name = 'Детектив'));
insert into BOOK_GENRES(isbn, genre_id) values (9785041172381, (select id from GENRES where genre_name = 'Детектив'));
insert into BOOK_GENRES(isbn, genre_id) values (9785041567637, (select id from GENRES where genre_name = 'Детектив'));

insert into BOOK_AUTHORS(isbn, author_id) values (9785699120147, (select id from AUTHORS where full_name = 'Русские народные сказки'));
insert into BOOK_AUTHORS(isbn, author_id) values (9785040941193, (select id from AUTHORS where full_name = 'Якоб и Вильгельм Гримм'));
insert into BOOK_AUTHORS(isbn, author_id) values (9785040941193, (select id from AUTHORS where full_name = 'Шарль Перро'));
insert into BOOK_AUTHORS(isbn, author_id) values (9785040941193, (select id from AUTHORS where full_name = 'Джозеф Джекобс'));
insert into BOOK_AUTHORS(isbn, author_id) values (9785040941193, (select id from AUTHORS where full_name = 'Андерсен Ханс Кристиан'));
insert into BOOK_AUTHORS(isbn, author_id) values (9785041060886, (select id from AUTHORS where full_name = 'Агата Кристи'));
insert into BOOK_AUTHORS(isbn, author_id) values (9785041172381, (select id from AUTHORS where full_name = 'Агата Кристи'));
insert into BOOK_AUTHORS(isbn, author_id) values (9785041567637, (select id from AUTHORS where full_name = 'Агата Кристи'));

INSERT INTO acl_sid (principal, sid) VALUES
(1, 'user'),
(1, 'admin');

INSERT INTO acl_class (class) VALUES
('ru.otus.homework.hw13.entity.Book');


INSERT INTO acl_object_identity (object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting) VALUES
(1, 9785040941193, null, (select id from acl_sid where sid = 'admin'), 1),
(1, 9785699120147, null, (select id from acl_sid where sid = 'admin'), 1),
(1, 9785041060886, null, (select id from acl_sid where sid = 'admin'), 1),
(1, 9785041172381, null, (select id from acl_sid where sid = 'admin'), 1),
(1, 9785041567637, null, (select id from acl_sid where sid = 'admin'), 1);

INSERT INTO acl_entry (acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) VALUES
((select id from acl_object_identity where object_id_identity = 9785040941193), 0, (select id from acl_sid where sid = 'admin'), 1,	1, 0, 0),
((select id from acl_object_identity where object_id_identity = 9785040941193), 1, (select id from acl_sid where sid = 'admin'), 2,	1, 0, 0),
((select id from acl_object_identity where object_id_identity = 9785040941193), 2, (select id from acl_sid where sid = 'user'), 1, 1, 0, 0),
((select id from acl_object_identity where object_id_identity = 9785699120147), 0, (select id from acl_sid where sid = 'admin'), 1,	1, 0, 0),
((select id from acl_object_identity where object_id_identity = 9785699120147), 1, (select id from acl_sid where sid = 'admin'), 2,	1, 0, 0),
((select id from acl_object_identity where object_id_identity = 9785699120147), 2, (select id from acl_sid where sid = 'user'), 1,	1, 0, 0),
((select id from acl_object_identity where object_id_identity = 9785041060886), 0, (select id from acl_sid where sid = 'admin'), 1,	1, 0, 0),
((select id from acl_object_identity where object_id_identity = 9785041060886), 1, (select id from acl_sid where sid = 'admin'), 2,	1, 0, 0),
((select id from acl_object_identity where object_id_identity = 9785041060886), 2, (select id from acl_sid where sid = 'user'), 1,	1, 0, 0),
((select id from acl_object_identity where object_id_identity = 9785041172381), 0, (select id from acl_sid where sid = 'admin'), 1,	1, 0, 0),
((select id from acl_object_identity where object_id_identity = 9785041172381), 1, (select id from acl_sid where sid = 'admin'), 2,	1, 0, 0),
((select id from acl_object_identity where object_id_identity = 9785041172381), 2, (select id from acl_sid where sid = 'user'), 1,	1, 0, 0),
((select id from acl_object_identity where object_id_identity = 9785041567637), 0, (select id from acl_sid where sid = 'admin'), 1,	1, 0, 0),
((select id from acl_object_identity where object_id_identity = 9785041567637), 1, (select id from acl_sid where sid = 'admin'), 2, 1, 0, 0),
((select id from acl_object_identity where object_id_identity = 9785041567637), 2, (select id from acl_sid where sid = 'user'), 1,	1, 0, 0)
;