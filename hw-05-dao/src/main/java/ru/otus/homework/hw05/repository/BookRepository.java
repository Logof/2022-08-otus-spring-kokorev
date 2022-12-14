package ru.otus.homework.hw05.repository;

import ru.otus.homework.hw05.entity.Book;

import java.util.List;

public interface BookRepository {

    Book getBookById(String isbn);

    void delete(String isbn);

    List<Book> getAll();

    int insert(Book book);

    int update(Book book);

    List<Book> getAllByAuthor(String fullName);

    List<Book> getAllByGenre(String genreName);

    void addAuthorToBook(String isbn, long authorId);

    void addGenreToBook(String isbn, long id);

}
