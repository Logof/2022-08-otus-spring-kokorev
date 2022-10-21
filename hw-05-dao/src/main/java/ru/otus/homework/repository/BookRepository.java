package ru.otus.homework.repository;

import ru.otus.homework.entity.Book;

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

    //List<Author> getBooksAuthors(String isbn);

    //List<Genre> getBookGenres(String isbn);
}
