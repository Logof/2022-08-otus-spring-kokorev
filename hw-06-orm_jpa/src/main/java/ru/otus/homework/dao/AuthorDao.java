package ru.otus.homework.dao;

import ru.otus.homework.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    long count();

    Optional<Author> getAuthorById(Long id);

    int delete(long id);

    List<Author> getAll();

    int insert(Author object);

    int update(Author object);

    long generateId();
}
