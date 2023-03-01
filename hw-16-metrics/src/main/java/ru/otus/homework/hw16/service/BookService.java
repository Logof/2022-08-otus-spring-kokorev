package ru.otus.homework.hw16.service;

import org.springframework.security.core.Authentication;
import ru.otus.homework.hw16.dto.BookDto;
import ru.otus.homework.hw16.entity.Book;

import java.util.List;

public interface BookService {
    Book save(Book book, Authentication authentication);

    List<BookDto> getAll();

    BookDto getByIsbn(Long isbn);

    void deleteById(Long isbn);
}
