insert into GENRES(id, genre_name) values (1, 'Сказки');
insert into AUTHORS(id, full_name) values (1, 'Русские народные сказки');
insert into AUTHORS(id, full_name) values (2, 'Якоб и Вильгельм Гримм');
insert into AUTHORS(id, full_name) values (3, 'Шарль Перро');
insert into AUTHORS(id, full_name) values (4, 'Джозеф Джекобс');
insert into AUTHORS(id, full_name) values (5, 'Андерсен Ханс Кристиан');

insert into BOOKS(isbn, title) values ('978-5-699-12014-7', 'Колобок');
insert into BOOKS(isbn, title) values ('978-5-04-094119-3', '100 лучших сказок всех времен и народов');

insert into ASSOC(isbn, external_id, external_class) values ('978-5-699-12014-7', 1, 'Genre');
insert into ASSOC(isbn, external_id, external_class) values ('978-5-699-12014-7', 1, 'Author');

insert into ASSOC(isbn, external_id, external_class) values ('978-5-04-094119-3', 1, 'Genre');
insert into ASSOC(isbn, external_id, external_class) values ('978-5-04-094119-3', 2, 'Author');
insert into ASSOC(isbn, external_id, external_class) values ('978-5-04-094119-3', 3, 'Author');
insert into ASSOC(isbn, external_id, external_class) values ('978-5-04-094119-3', 4, 'Author');
insert into ASSOC(isbn, external_id, external_class) values ('978-5-04-094119-3', 5, 'Author');
