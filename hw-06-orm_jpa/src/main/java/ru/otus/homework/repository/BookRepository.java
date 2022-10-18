package ru.otus.homework.repository;

import ru.otus.homework.entity.Book;

import java.util.List;

public interface BookRepository {
    Book getBookByIsbn(String isbn);

    void deleteByIsbn(String isbn);

    List<Book> getAll();

    Book update(Book book);

    void insert(Book book);

    List<Book> getAllByAuthor(String fullName);

    List<Book> getAllByGenre(String genreName);
}
