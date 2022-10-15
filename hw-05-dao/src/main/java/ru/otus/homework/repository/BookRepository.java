package ru.otus.homework.repository;

import ru.otus.homework.entity.Book;

import java.util.List;

public interface BookRepository {
    long count();

    Book getBookById(String isbn);

    void delete(String isbn);

    List<Book> getAll();

    int insert(Book book);

    int update(Book book);

    List<Book> getAllByAuthor(String fullName);

    List<Book> getAllByGenre(String genreName);
}
