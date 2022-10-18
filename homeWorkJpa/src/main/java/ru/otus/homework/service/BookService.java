package ru.otus.homework.service;

import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.entity.Book;

public interface BookService {

    void getAll();

    void getById(String isbn);

    void add(Book book);
}
