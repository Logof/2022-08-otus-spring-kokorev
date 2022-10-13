package ru.otus.homework.repository;

import ru.otus.homework.entity.Author;

import java.util.List;

public interface AuthorDao {
    long count();

    Author getAuthorById(long id);

    void delete(long id);

    List<Author> getAll();

    List<Author> getAuthorsByIsbn(String isbn);

    boolean isAttachedToBook(long id);

    int insert(Author object);

    int update(Author object);

    long generateId();
}
