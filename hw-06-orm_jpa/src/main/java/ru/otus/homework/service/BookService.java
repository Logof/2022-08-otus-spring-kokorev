package ru.otus.homework.service;

import ru.otus.homework.entity.Book;

public interface BookService {
    void addNewBook(Book book);

    void deleteByIsbn(String isbn);

    void getAll();

    void getById(String isbn);
}
