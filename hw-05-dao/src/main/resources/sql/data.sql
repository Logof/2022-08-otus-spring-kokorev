insert into GENRES(genre_name) values ('Сказки');
insert into AUTHORS(full_name) values ('Русские народные сказки');
insert into AUTHORS(full_name) values ('Якоб и Вильгельм Гримм');
insert into AUTHORS(full_name) values ('Шарль Перро');
insert into AUTHORS(full_name) values ('Джозеф Джекобс');
insert into AUTHORS(full_name) values ('Андерсен Ханс Кристиан');

insert into BOOKS(isbn, title) values ('978-5-699-12014-7', 'Колобок');
insert into BOOKS(isbn, title) values ('978-5-04-094119-3', '100 лучших сказок всех времен и народов');

insert into ASSOC(isbn, external_id, external_class) values ('978-5-699-12014-7', (select id from GENRES where genre_name = 'Сказки'), 'Genre');
insert into ASSOC(isbn, external_id, external_class) values ('978-5-699-12014-7', (select id from AUTHORS where full_name = 'Русские народные сказки'), 'Author');

insert into ASSOC(isbn, external_id, external_class) values ('978-5-04-094119-3', (select id from GENRES where genre_name = 'Сказки'), 'Genre');
insert into ASSOC(isbn, external_id, external_class) values ('978-5-04-094119-3', (select id from AUTHORS where full_name = 'Якоб и Вильгельм Гримм'), 'Author');
insert into ASSOC(isbn, external_id, external_class) values ('978-5-04-094119-3', (select id from AUTHORS where full_name = 'Шарль Перро'), 'Author');
insert into ASSOC(isbn, external_id, external_class) values ('978-5-04-094119-3', (select id from AUTHORS where full_name = 'Джозеф Джекобс'), 'Author');
insert into ASSOC(isbn, external_id, external_class) values ('978-5-04-094119-3', (select id from AUTHORS where full_name = 'Андерсен Ханс Кристиан'), 'Author');
