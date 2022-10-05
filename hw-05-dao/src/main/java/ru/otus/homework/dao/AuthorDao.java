package ru.otus.homework.dao;

import ru.otus.homework.entity.Author;

import java.util.List;

public interface AuthorDao {
    long count();

    Author getAuthorById(Long id);

    void delete(long id);

    List<Author> getAll();

    void insert(Author object);

    void update(Author object);

    long generateId();
}
