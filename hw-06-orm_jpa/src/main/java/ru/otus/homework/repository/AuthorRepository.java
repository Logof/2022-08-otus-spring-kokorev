package ru.otus.homework.repository;

import ru.otus.homework.entity.Author;

import java.util.Set;

public interface AuthorRepository {
    Author getAuthorById(long id);

    Author getByFullName(String fullName);

    void delete(long id);

    Set<Author> getAll();

    Set<Author> getAuthorsByIsbn(String isbn);

    boolean isAttachedToBook(long id);

    Author save(Author object);
}
