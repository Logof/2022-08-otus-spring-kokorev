insert into GENRES(id, genre_name) values (1, 'Сказки');

insert into AUTHORS(id, full_name) values (1, 'Русские народные сказки');
insert into AUTHORS(id, full_name) values (2, 'Якоб и Вильгельм Гримм');
insert into AUTHORS(id, full_name) values (3, 'Шарль Перро');
insert into AUTHORS(id, full_name) values (4, 'Джозеф Джекобс');
insert into AUTHORS(id, full_name) values (5, 'Андерсен Ханс Кристиан');

insert into COMMENTS(id, comment_text) values (1, 'Комментарий 1');
insert into COMMENTS(id, comment_text) values (2, 'Комментарий 2');
insert into COMMENTS(id, comment_text) values (3, 'Комментарий 3');
insert into COMMENTS(id, comment_text) values (4, 'Комментарий 4');
insert into COMMENTS(id, comment_text) values (5, 'Комментарий 5');
insert into COMMENTS(id, comment_text) values (6, 'Комментарий 6');
insert into COMMENTS(id, comment_text) values (7, 'Комментарий 7');
insert into COMMENTS(id, comment_text) values (8, 'Комментарий 8');
insert into COMMENTS(id, comment_text) values (9, 'Комментарий 9');
insert into COMMENTS(id, comment_text) values (10, 'Комментарий 10');

insert into BOOKS(isbn, title) values ('978-5-699-12014-7', 'Колобок');
insert into BOOKS(isbn, title) values ('978-5-04-094119-3', '100 лучших сказок всех времен и народов');

insert into ASSOC(isbn, external_id, external_class) values ('978-5-699-12014-7', 1, 'Genre');
insert into ASSOC(isbn, external_id, external_class) values ('978-5-699-12014-7', 1, 'Author');
insert into ASSOC(isbn, external_id, external_class) values ('978-5-699-12014-7', 1, 'Comment');
insert into ASSOC(isbn, external_id, external_class) values ('978-5-699-12014-7', 2, 'Comment');
insert into ASSOC(isbn, external_id, external_class) values ('978-5-699-12014-7', 3, 'Comment');
insert into ASSOC(isbn, external_id, external_class) values ('978-5-699-12014-7', 4, 'Comment');
insert into ASSOC(isbn, external_id, external_class) values ('978-5-699-12014-7', 5, 'Comment');
insert into ASSOC(isbn, external_id, external_class) values ('978-5-699-12014-7', 6, 'Comment');

insert into ASSOC(isbn, external_id, external_class) values ('978-5-04-094119-3', 1, 'Genre');
insert into ASSOC(isbn, external_id, external_class) values ('978-5-04-094119-3', 2, 'Author');
insert into ASSOC(isbn, external_id, external_class) values ('978-5-04-094119-3', 3, 'Author');
insert into ASSOC(isbn, external_id, external_class) values ('978-5-04-094119-3', 4, 'Author');
insert into ASSOC(isbn, external_id, external_class) values ('978-5-04-094119-3', 5, 'Author');
insert into ASSOC(isbn, external_id, external_class) values ('978-5-04-094119-3', 7, 'Comment');
insert into ASSOC(isbn, external_id, external_class) values ('978-5-04-094119-3', 8, 'Comment');
insert into ASSOC(isbn, external_id, external_class) values ('978-5-04-094119-3', 9, 'Comment');
insert into ASSOC(isbn, external_id, external_class) values ('978-5-04-094119-3', 10, 'Comment');