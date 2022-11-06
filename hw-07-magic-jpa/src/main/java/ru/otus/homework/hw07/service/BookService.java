package ru.otus.homework.hw07.service;

import ru.otus.homework.hw07.entity.Comment;
import ru.otus.homework.hw07.entity.dto.BookDto;

import java.util.List;

public interface BookService {

    BookDto updateTitle(String isbn, String newTitle);

    BookDto add(BookDto book);

    void deleteByIsbn(String isbn);

    List<BookDto> getAll();

    BookDto getByIsbn(String isbn);

    List<BookDto> getAllByAuthor(String fullName);

    List<BookDto> getAllByGenre(String genreName);

    BookDto addGenreToBook(String isbn, String genreName);

    BookDto addAuthorToBook(String isbn, String fullName);

    Comment addCommentToBook(String isbn, String commentText);
}
