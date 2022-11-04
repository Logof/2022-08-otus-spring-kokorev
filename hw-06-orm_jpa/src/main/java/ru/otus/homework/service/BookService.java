package ru.otus.homework.service;

import ru.otus.homework.entity.Book;

public interface BookService {

    void updateTitle(Book book);

    void add(Book book);

    void deleteByIsbn(String isbn);

    void getAll();

    void getByIsbn(String isbn);

    void getAllByAuthor(String fullName);

    void getAllByGenre(String genreName);

    void addGenreToBook(String isbn, String genreName);

    void addAuthorToBook(String isbn, String fullName);

    void addCommentToBook(String isbn, String commentText);
}
