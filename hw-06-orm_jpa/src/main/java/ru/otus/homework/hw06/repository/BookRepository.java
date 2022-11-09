package ru.otus.homework.hw06.repository;

import ru.otus.homework.hw06.entity.Book;

import java.util.Set;

public interface BookRepository {
    Book getBookByIsbn(String isbn);

    void deleteByIsbn(String isbn);

    Set<Book> getAll();

    Book update(Book book);

    void insert(Book book);

    Set<Book> getAllByAuthor(String fullName);

    Set<Book> getAllByGenre(String genreName);
}
