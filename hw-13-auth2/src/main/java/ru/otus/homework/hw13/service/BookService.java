package ru.otus.homework.hw13.service;

import org.springframework.security.core.Authentication;
import ru.otus.homework.hw13.entity.Book;

import java.util.List;

public interface BookService {
    Book save(Book book, Authentication authentication);

    List<Book> getAll();

    Book getByIsbn(long isbn);

    void deleteById(long isbn);
}
