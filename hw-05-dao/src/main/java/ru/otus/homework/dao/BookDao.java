package ru.otus.homework.dao;

import ru.otus.homework.entity.Book;

import java.util.List;

public interface BookDao {
    long count();

    Book getBookById(String isbn);

    void delete(String isbn);

    List<Book> getAll();

    int insert(Book book);

    int update(Book book);

}
