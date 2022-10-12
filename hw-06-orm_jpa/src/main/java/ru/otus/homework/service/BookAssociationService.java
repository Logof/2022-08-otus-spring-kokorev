package ru.otus.homework.service;

public interface BookAssociationService {
    void addAuthorAssoc(String isbn, long authorId);

    void addGenreAssoc(String isbn, long genreId);

    void removeAuthor(String isbn, long authorId);

    void removeGenre(String isbn, long genreId);

    void updateIsbnExternalLinks(String isbn, String newIsbn);
}
