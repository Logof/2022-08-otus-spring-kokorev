package ru.otus.homework.dao;

import ru.otus.homework.entity.Author;
import ru.otus.homework.entity.BookAssociation;
import ru.otus.homework.entity.Genre;

import java.util.List;

public interface BookAssociationDao {

    boolean isExist(String isbn, long authorId, String className);

    void delete(String isbn, long authorId, String className);

    void insert(BookAssociation bookAssociation);

    List<Long> getExternalIdsById(String isbn, String authorClassName);

    List<Author> getAutors(String isbn);

    List<Genre> getGenres(String isbn);

    boolean isExistExternalLink(long externalId, String className);
}
