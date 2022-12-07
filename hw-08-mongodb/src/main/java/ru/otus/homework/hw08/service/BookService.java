package ru.otus.homework.hw08.service;

import ru.otus.homework.hw08.entity.Book;

import java.util.List;

public interface BookService {

    Book updateTitle(String isbn, String newTitle);

    Book add(Book book);

    void deleteByIsbn(String isbn);

    List<Book> getAll();

    Book getByIsbn(String isbn);

    List<Book> getAllByAuthor(String fullName);

    List<Book> getAllByGenre(String genreName);

    Book addGenreToBook(String isbn, String genreName);

    Book addAuthorToBook(String isbn, String fullName);
}
