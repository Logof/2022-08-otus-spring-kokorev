package ru.otus.homework.hw11.contoller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.homework.hw11.entity.Author;
import ru.otus.homework.hw11.service.AuthorService;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthorRestController {

    private final AuthorService authorService;

    @GetMapping(path = "/api/author")
    public List<Author> getAllAuthors() {
        return authorService.getAll();
    }

}
