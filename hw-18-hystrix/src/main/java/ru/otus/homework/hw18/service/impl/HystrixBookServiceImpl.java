package ru.otus.homework.hw18.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.otus.homework.hw18.dto.BookDto;
import ru.otus.homework.hw18.entity.Book;
import ru.otus.homework.hw18.service.BookService;

import java.util.ArrayList;
import java.util.List;

@Service
public class HystrixBookServiceImpl implements BookService {

    private final BookService bookService;

    public HystrixBookServiceImpl(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public Book save(Book book, Authentication authentication) {
        return bookService.save(book, authentication);
    }

    @Override
    @HystrixCommand(commandKey="getFallBook", fallbackMethod="fallback")
    public List<BookDto> getAll() {
        return bookService.getAll();
    }

    @Override
    @HystrixCommand(commandKey="getFallBook", fallbackMethod="fallbackId")
    public BookDto getByIsbn(Long isbn) {
        return bookService.getByIsbn(isbn);
    }

    @Override
    public void deleteById(Long isbn) {
        bookService.deleteById(isbn);
    }


    private List<BookDto> fallback() {
        return new ArrayList<>();
    }

    private BookDto fallbackId(Long isbn) {
        return new BookDto(isbn, "N/A", new ArrayList<>(), new ArrayList<>());
    }
}
