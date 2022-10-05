package ru.otus.homework.service;

public interface BookService {

    void add(String isbn, String title);

    void delete(String isbn);

    void outputAll();

    void output(String isbn);

}
