package ru.otus.homework.service;

import ru.otus.homework.entity.Author;
import ru.otus.homework.entity.Book;

public interface AuthorService {
    void deleteAuthorFromBook(Book book, long authorId);

    void deleteAuthor(long authorId);

    void addAuthorToBook(Book book, Author author);

    void addAuthor(String fullName);

    void outputAll();
}
