package ru.otus.homework.hw10.contoller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.otus.homework.hw10.entity.dto.BookDto;
import ru.otus.homework.hw10.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/book")
@Slf4j
@RequiredArgsConstructor
public class BookRestController {

    private final BookService bookService;

    @GetMapping
    public List<BookDto> getAllBook() {
        return bookService.getAll();
    }

    @GetMapping(path = "{isbn}")
    public BookDto getBookByIsbn(@PathVariable("isbn") String isbn) {
        return bookService.getByIsbn(isbn);
    }

    @PostMapping
    public void saveBook(@RequestBody BookDto bookDto) {
        bookService.save(bookDto);
    }

    @DeleteMapping(path = "{isbn}")
    public void deleteBook(@PathVariable("isbn") String isbn) {
        bookService.deleteByIsbn(isbn);
    }
}
