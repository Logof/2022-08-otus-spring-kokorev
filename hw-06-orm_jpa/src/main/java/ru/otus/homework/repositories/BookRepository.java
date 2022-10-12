package ru.otus.homework.repositories;


import ru.otus.homework.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book save(Book book);

    Optional<Book> findByIsbn(String isbn);

    List<Book> findAll();

    void updateTitleById(String isbn, String title);

    void deleteByIsbn(String isbn);
}
