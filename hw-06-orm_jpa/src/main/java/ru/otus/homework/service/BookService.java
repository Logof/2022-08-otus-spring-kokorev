package ru.otus.homework.service;

import ru.otus.homework.entity.Book;

public interface BookService {
    void saveBook(Book book);

    void deleteByIsbn(String isbn);

    void getAll();

    void getByIsbn(String isbn);
}
