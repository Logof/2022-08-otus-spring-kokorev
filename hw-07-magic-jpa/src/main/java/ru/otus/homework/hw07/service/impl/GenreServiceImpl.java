package ru.otus.homework.hw07.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.hw07.entity.Genre;
import ru.otus.homework.hw07.repository.GenreRepository;
import ru.otus.homework.hw07.service.GenreService;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    @Transactional
    public void delete(long genreId) {
        genreRepository.deleteById(genreId);
    }

    @Override
    @Transactional
    public Genre add(String genreName) {
        return genreRepository.save(new Genre(genreName));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

}
