package ru.otus.homework.dao;

import ru.otus.homework.entity.Author;
import ru.otus.homework.entity.BookAssociation;
import ru.otus.homework.entity.Genre;

import java.util.List;

public interface BookAssociationDao {

    boolean isExist(BookAssociation bookAssociation);

    int delete(BookAssociation bookAssociation);

    int insert(BookAssociation bookAssociation);

    List<Long> getExternalIdsById(String isbn, String authorClassName);

    List<Author> getAutors(String isbn);

    List<Genre> getGenres(String isbn);

    int updateIsbnExternalLinks(String isbn, String newIsbn);
}
