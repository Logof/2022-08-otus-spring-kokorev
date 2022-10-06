package ru.otus.homework.dao;

import ru.otus.homework.entity.Author;

import java.util.List;

public interface AuthorDao {
    long count();

    Author getAuthorById(Long id);

    void delete(long id);

    List<Author> getAll();

    int insert(Author object);

    int update(Author object);

    long generateId();
}
