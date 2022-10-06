package ru.otus.homework.service;

import ru.otus.homework.entity.Book;

public interface BookService {

    void update(String isbn, Book book);

    void add(Book book);

    void deleteById(String isbn);

    void getAll();

    void getById(String isbn);

}
