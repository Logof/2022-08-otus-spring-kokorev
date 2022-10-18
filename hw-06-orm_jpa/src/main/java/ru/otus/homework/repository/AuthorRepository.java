package ru.otus.homework.repository;

import ru.otus.homework.entity.Author;

import java.util.List;

public interface AuthorRepository {
    Author getAuthorById(long id);

    Author getByFullName(String fullName);

    void delete(long id);

    List<Author> getAll();

    List<Author> getAuthorsByIsbn(String isbn);

    boolean isAttachedToBook(long id);

    Author save(Author object);
}
