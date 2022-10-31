package ru.otus.homework.service;

import ru.otus.homework.entity.Book;

public interface BookService {

    void getAll();

    void getAllByAuthor(String fullName);

    void getAllByGenre(String genreName);

    void getById(String isbn);

    void updateTitle(Book book);

    void deleteById(String isbn);

    void add(Book book);

    void addGenreToBook(String isbn, String genreName);

    void addAuthorToBook(String isbn, String fullName);
}
