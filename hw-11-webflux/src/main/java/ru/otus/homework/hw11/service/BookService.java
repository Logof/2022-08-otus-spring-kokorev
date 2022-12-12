package ru.otus.homework.hw11.service;

import ru.otus.homework.hw11.entity.Book;

import java.util.List;

public interface BookService {

        void updateTitle(String isbn, String newTitle);

        Book save(Book book);

        void deleteByIsbn(String isbn);

        List<Book> getAll();

        Book getByIsbn(String isbn);

        List<Book> getAllByAuthor(String fullName);

        List<Book> getAllByGenre(String genreName);

        Book addGenreToBook(String isbn, String genreName);

        Book addAuthorToBook(String isbn, String fullName);
    }
