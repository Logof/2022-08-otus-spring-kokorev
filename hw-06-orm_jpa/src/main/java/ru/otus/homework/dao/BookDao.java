package ru.otus.homework.dao;

import ru.otus.homework.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    long count();

    Optional<Book> getBookById(String isbn);

    int delete(String isbn);

    List<Book> getAll();

    int insert(Book book);

    int update(Book book);

}
