insert into ROLES(name) values ('USER');
insert into ROLES(name) values ('ADMIN');

insert into USERS(user_name, password) values ('user', '$2a$10$bfhDL0Vbp13GO0IJAntFkuAK765RtGRHsG0IjrSlUBbsd5dUc9t9i');
insert into USERS(user_name, password) values ('admin', '$2a$10$bfhDL0Vbp13GO0IJAntFkuAK765RtGRHsG0IjrSlUBbsd5dUc9t9i');

insert into USER_ROLES(user_name, role_id) values ('user', (select id from ROLES where name = 'USER'));
insert into USER_ROLES(user_name, role_id) values ('admin', (select id from ROLES where name = 'ADMIN'));

insert into GENRES(genre_name) values ('Сказки');
insert into GENRES(genre_name) values ('Детектив');
insert into AUTHORS(full_name) values ('Русские народные сказки');
insert into AUTHORS(full_name) values ('Якоб и Вильгельм Гримм');
insert into AUTHORS(full_name) values ('Шарль Перро');
insert into AUTHORS(full_name) values ('Джозеф Джекобс');
insert into AUTHORS(full_name) values ('Андерсен Ханс Кристиан');
insert into AUTHORS(full_name) values ('Агата Кристи');

insert into BOOKS(isbn, title) values ('978-5-699-12014-7', 'Колобок');
insert into BOOKS(isbn, title) values ('978-5-04-094119-3', '100 лучших сказок всех времен и народов');
insert into BOOKS(isbn, title) values ('978-5-04-106088-6', 'Вечеринка в Хэллоуин');
insert into BOOKS(isbn, title) values ('978-5-04-117238-1', 'Рождество Эркюля Пуаро');
insert into BOOKS(isbn, title) values ('978-5-04-156763-7', 'Слоны умеют помнить');

insert into BOOK_GENRES(isbn, genre_id) values ('978-5-699-12014-7', (select id from GENRES where genre_name = 'Сказки'));
insert into BOOK_GENRES(isbn, genre_id) values ('978-5-04-106088-6', (select id from GENRES where genre_name = 'Детектив'));
insert into BOOK_GENRES(isbn, genre_id) values ('978-5-04-117238-1', (select id from GENRES where genre_name = 'Детектив'));
insert into BOOK_GENRES(isbn, genre_id) values ('978-5-04-156763-7', (select id from GENRES where genre_name = 'Детектив'));

insert into BOOK_AUTHORS(isbn, author_id) values ('978-5-699-12014-7', (select id from AUTHORS where full_name = 'Русские народные сказки'));

insert into BOOK_AUTHORS(isbn, author_id) values ('978-5-04-106088-6', (select id from AUTHORS where full_name = 'Агата Кристи'));
insert into BOOK_AUTHORS(isbn, author_id) values ('978-5-04-117238-1', (select id from AUTHORS where full_name = 'Агата Кристи'));
insert into BOOK_AUTHORS(isbn, author_id) values ('978-5-04-156763-7', (select id from AUTHORS where full_name = 'Агата Кристи'));

insert into BOOK_GENRES(isbn, genre_id) values ('978-5-04-094119-3', (select id from GENRES where genre_name = 'Сказки'));
insert into BOOK_AUTHORS(isbn, author_id) values ('978-5-04-094119-3', (select id from AUTHORS where full_name = 'Якоб и Вильгельм Гримм'));
insert into BOOK_AUTHORS(isbn, author_id) values ('978-5-04-094119-3', (select id from AUTHORS where full_name = 'Шарль Перро'));
insert into BOOK_AUTHORS(isbn, author_id) values ('978-5-04-094119-3', (select id from AUTHORS where full_name = 'Джозеф Джекобс'));
insert into BOOK_AUTHORS(isbn, author_id) values ('978-5-04-094119-3', (select id from AUTHORS where full_name = 'Андерсен Ханс Кристиан'));

insert into COMMENTS(isbn, comment_text) values ('978-5-04-094119-3', 'Классный сборник сказок Андерсена');
insert into COMMENTS(isbn, comment_text) values ('978-5-04-094119-3', 'Классный сборник сказок!!!');
insert into COMMENTS(isbn, comment_text) values ('978-5-04-094119-3', 'Братья Гримм - форевер');
insert into COMMENTS(isbn, comment_text) values ('978-5-04-094119-3', 'Подарите мне эту книжку на день рождения. Костя 3 года');