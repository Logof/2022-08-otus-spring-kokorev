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
import ru.otus.homework.hw11.entity.dto.GenreDto;
import ru.otus.homework.hw11.mapper.GenreMapper;
import ru.otus.homework.hw11.repository.GenreRepository;

@RestController
@Slf4j
@RequiredArgsConstructor
public class GenreRestController {

    private final GenreRepository genreRepository;

    private final GenreMapper genreMapper;

    @GetMapping(path = "/api/genre")
    public Flux<GenreDto> getAllGenres() {
        return genreRepository.findAll().map(genre -> genreMapper.toDto(genre));
    }

    @PostMapping(path = "/api/genre")
    public Mono<ResponseEntity<GenreDto>> saveAuthor(@RequestBody GenreDto genreDto) {
        return genreRepository.save(genreMapper.toEntity(genreDto))
                .map(genre -> genreMapper.toDto(genre))
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }
}
