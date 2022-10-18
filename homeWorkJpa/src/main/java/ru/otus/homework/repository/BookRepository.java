package ru.otus.homework.repository;

import ru.otus.homework.entity.Book;

import java.util.List;

public interface BookRepository {
    List<Book> getAll();

    Book getByIsbn(String isbn);

    void save(Book book);
}
