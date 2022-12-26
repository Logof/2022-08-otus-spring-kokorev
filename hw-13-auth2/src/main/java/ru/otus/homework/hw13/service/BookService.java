package ru.otus.homework.hw13.service;

import org.springframework.security.core.Authentication;
import ru.otus.homework.hw13.dto.BookDto;
import ru.otus.homework.hw13.entity.Book;

import java.util.List;

public interface BookService {
    Book save(Book book, Authentication authentication);

    List<BookDto> getAll();

    BookDto getByIsbn(long isbn);

    void deleteById(long isbn);
}
