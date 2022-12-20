package ru.otus.homework.hw11.contoller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.homework.hw11.entity.dto.AuthorDto;
import ru.otus.homework.hw11.mapper.AuthorMapper;
import ru.otus.homework.hw11.repository.AuthorRepository;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthorRestController {

    private final AuthorRepository authorRepository;

    private final AuthorMapper authorMapper;

    @GetMapping(path = "/api/author")
    public Flux<AuthorDto> getAllAuthors() {
        return authorRepository.findAll().map(genre -> authorMapper.toDto(genre));
    }

    @PostMapping(path = "/api/author")
    public Mono<ResponseEntity<AuthorDto>> saveAuthor(@RequestBody AuthorDto authorDto) {
        return authorRepository.save(authorMapper.toEntity(authorDto))
                .map(author -> authorMapper.toDto(author))
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

}
