package ru.otus.homework.hw18.service;

import org.springframework.security.core.Authentication;
import ru.otus.homework.hw18.dto.BookDto;
import ru.otus.homework.hw18.entity.Book;

import java.util.List;

public interface BookService {
    Book save(Book book, Authentication authentication);

    List<BookDto> getAll();

    BookDto getByIsbn(Long isbn);

    void deleteById(Long isbn);
}
