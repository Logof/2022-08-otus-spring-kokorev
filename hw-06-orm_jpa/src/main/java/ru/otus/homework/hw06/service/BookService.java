package ru.otus.homework.hw06.service;

import ru.otus.homework.hw06.entity.Book;

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
