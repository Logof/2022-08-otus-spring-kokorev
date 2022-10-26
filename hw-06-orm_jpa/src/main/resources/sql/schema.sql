ALTER TABLE IF EXISTS BOOK_GENRES DROP CONSTRAINT BOOK_GENRES_FK_1;
ALTER TABLE IF EXISTS BOOK_GENRES DROP CONSTRAINT BOOK_GENRES_FK_2;

ALTER TABLE IF EXISTS BOOK_AUTHORS DROP CONSTRAINT BOOK_AUTHORS_FK_1;
ALTER TABLE IF EXISTS BOOK_AUTHORS DROP CONSTRAINT BOOK_AUTHORS_FK_2;

DROP TABLE IF EXISTS BOOKS;
CREATE TABLE IF NOT EXISTS BOOKS (ISBN VARCHAR(17) NOT NULL, TITLE VARCHAR(100) NOT NULL, CONSTRAINT BOOKS_PK PRIMARY KEY (ISBN));

DROP TABLE IF EXISTS AUTHORS;
CREATE TABLE IF NOT EXISTS AUTHORS (ID BIGINT NOT NULL AUTO_INCREMENT, FULL_NAME VARCHAR(100) NOT NULL, CONSTRAINT AUTHORS_PK PRIMARY KEY (ID));
CREATE UNIQUE INDEX U_AUTHORS_FULL_NAME ON AUTHORS (FULL_NAME);

DROP TABLE IF EXISTS GENRES;
CREATE TABLE IF NOT EXISTS GENRES (ID BIGINT NOT NULL AUTO_INCREMENT, GENRE_NAME VARCHAR(100) NOT NULL, CONSTRAINT GENRES_PK PRIMARY KEY (ID));
CREATE UNIQUE INDEX U_GENRES_GENRE_NAME ON GENRES (GENRE_NAME);

DROP TABLE IF EXISTS BOOK_AUTHORS;
CREATE TABLE IF NOT EXISTS BOOK_AUTHORS (ISBN VARCHAR(17) NOT NULL,
                                        AUTHOR_ID BIGINT NOT NULL,
                                        CONSTRAINT BOOK_AUTHORS_PK PRIMARY KEY (ISBN,AUTHOR_ID),
                                        CONSTRAINT BOOK_AUTHORS_FK_1 FOREIGN KEY (ISBN) REFERENCES BOOKS(ISBN) ON DELETE CASCADE ON UPDATE RESTRICT,
                                        CONSTRAINT BOOK_AUTHORS_FK_2 FOREIGN KEY (AUTHOR_ID) REFERENCES AUTHORS(ID) ON DELETE RESTRICT ON UPDATE RESTRICT);

DROP TABLE IF EXISTS BOOK_GENRES;
CREATE TABLE IF NOT EXISTS BOOK_GENRES (ISBN VARCHAR(17) NOT NULL,
                                        GENRE_ID BIGINT NOT NULL,
                                        CONSTRAINT BOOK_GENRES_PK PRIMARY KEY (ISBN,GENRE_ID),
                                        CONSTRAINT BOOK_GENRES_FK_1 FOREIGN KEY (ISBN) REFERENCES BOOKS(ISBN) ON DELETE CASCADE ON UPDATE RESTRICT,
                                        CONSTRAINT BOOK_GENRES_FK_2 FOREIGN KEY (GENRE_ID) REFERENCES GENRES(ID) ON DELETE RESTRICT ON UPDATE RESTRICT);

DROP TABLE IF EXISTS COMMENTS;
CREATE TABLE IF NOT EXISTS COMMENTS (ID BIGINT NOT NULL AUTO_INCREMENT,
                                     ISBN VARCHAR(17),
                                     COMMENT_TEXT VARCHAR(1000) NOT NULL,
                                     CONSTRAINT COMMENTS_PK PRIMARY KEY (ID),
                                     CONSTRAINT COMMENTS_FK_1 FOREIGN KEY (ISBN) REFERENCES BOOKS(ISBN) ON DELETE CASCADE ON UPDATE RESTRICT);