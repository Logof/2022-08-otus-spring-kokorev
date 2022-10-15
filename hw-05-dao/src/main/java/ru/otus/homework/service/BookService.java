package ru.otus.homework.service;

import ru.otus.homework.entity.Book;

import java.util.List;

public interface BookService {

    void updateTitle(Book book);

    void add(Book book);

    void add(Book book, List<String> authors, List<String> genres);

    void deleteById(String isbn);

    void getAll();

    void getById(String isbn);

}
