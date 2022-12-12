package ru.otus.homework.hw11.contoller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.otus.homework.hw11.entity.Book;
import ru.otus.homework.hw11.service.BookService;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class BookRestController {

    private final BookService bookService;

    @GetMapping(path = "/api/book")
    public List<Book> getAllBook() {
        return bookService.getAll();
    }

    @GetMapping(path = "/api/book/{isbn}")
    public Book getBookByIsbn(@PathVariable("isbn") String isbn) {
        return bookService.getByIsbn(isbn);
    }

    @PostMapping(path = "/api/book")
    public void saveBook(@RequestBody Book Book) {
        bookService.save(Book);
    }

    @DeleteMapping(path = "/api/book/{isbn}")
    public void deleteBook(@PathVariable("isbn") String isbn) {
        bookService.deleteByIsbn(isbn);
    }
}
