package ru.otus.homework.service.impl;

import ru.otus.homework.repositories.GenreRepository;
import ru.otus.homework.service.GenreService;

public class GenreServiceImpl implements GenreService {

    private final GenreRepository repository;

    public GenreServiceImpl(GenreRepository repository) {
        this.repository = repository;
    }


}
