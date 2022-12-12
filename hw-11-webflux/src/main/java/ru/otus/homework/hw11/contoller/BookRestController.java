package ru.otus.homework.hw11.contoller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.otus.homework.hw11.entity.dto.BookDto;
import ru.otus.homework.hw11.service.BookService;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class BookRestController {

    private final BookService bookService;

    @GetMapping(path = "/api/book")
    public List<BookDto> getAllBook() {
        return bookService.getAll();
    }

    @GetMapping(path = "/api/book/{isbn}")
    public BookDto getBookByIsbn(@PathVariable("isbn") String isbn) {
        return bookService.getByIsbn(isbn);
    }

    @PostMapping(path = "/api/book")
    public void saveBook(@RequestBody BookDto bookDto) {
        bookService.save(bookDto);
    }

    @DeleteMapping(path = "/api/book/{isbn}")
    public void deleteBook(@PathVariable("isbn") String isbn) {
        bookService.deleteByIsbn(isbn);
    }
}
