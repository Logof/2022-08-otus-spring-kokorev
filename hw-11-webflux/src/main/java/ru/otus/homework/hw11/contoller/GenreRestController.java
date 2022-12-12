package ru.otus.homework.hw11.contoller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.homework.hw11.entity.Genre;
import ru.otus.homework.hw11.service.GenreService;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class GenreRestController {

    private final GenreService genreService;

    @GetMapping(path = "/api/genre")
    public List<Genre> getAllGenres() {
        return genreService.getAll();
    }
}
