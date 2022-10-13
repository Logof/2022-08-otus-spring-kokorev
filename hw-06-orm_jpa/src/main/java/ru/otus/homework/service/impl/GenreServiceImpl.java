package ru.otus.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.homework.repositories.GenreRepository;
import ru.otus.homework.service.GenreService;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository repository;

    public GenreServiceImpl(GenreRepository repository) {
        this.repository = repository;
    }


}
