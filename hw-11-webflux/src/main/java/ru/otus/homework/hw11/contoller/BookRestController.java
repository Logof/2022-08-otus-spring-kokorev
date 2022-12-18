package ru.otus.homework.hw11.contoller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.homework.hw11.entity.dto.BookDto;
import ru.otus.homework.hw11.mapper.BookMapper;
import ru.otus.homework.hw11.repository.BookRepository;
import ru.otus.homework.hw11.repository.CommentRepository;

@RestController
@Slf4j
@RequiredArgsConstructor
public class BookRestController {
    private final CommentRepository commentRepository;

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    @GetMapping(path = "/api/book")
    public Flux<BookDto> getAllBook() {
        return bookRepository.findAll()
                .map(book -> bookMapper.toDto(book));
    }

    @GetMapping(path = "/api/book/{isbn}")
    public Mono<ResponseEntity<BookDto>> getBookByIsbn(@PathVariable("isbn") String isbn) {
        return bookRepository.findById(isbn)
                .map(book -> bookMapper.toDto(book))
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @PostMapping(path = "/api/book")
    public Mono<ResponseEntity<BookDto>> saveBook(@RequestBody BookDto bookDto) {
       return bookRepository.save(bookMapper.toEntity(bookDto))
               .map(book -> bookMapper.toDto(book))
               .map(ResponseEntity::ok)
               .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @DeleteMapping(path = "/api/book/{isbn}")
    public boolean deleteBook(@PathVariable("isbn") String isbn) {
        if (isbn != null) {
            commentRepository.findAllByBook_Isbn(isbn);
            bookRepository.deleteById(isbn).subscribe();
            return true;
        }
        return false;
    }
}
