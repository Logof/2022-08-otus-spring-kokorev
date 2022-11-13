package ru.otus.homework.hw09.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.hw09.entity.Genre;
import ru.otus.homework.hw09.entity.dto.GenreDto;
import ru.otus.homework.hw09.mapper.GenreMapper;
import ru.otus.homework.hw09.repository.GenreRepository;
import ru.otus.homework.hw09.service.GenreService;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreMapper mapper;
    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreMapper mapper,
                            GenreRepository genreRepository) {
        this.mapper = mapper;
        this.genreRepository = genreRepository;
    }

    @Override
    @Transactional
    public void delete(long genreId) {
        genreRepository.deleteById(genreId);
    }

    @Override
    @Transactional
    public GenreDto add(String genreName) {
        return mapper.toDto(genreRepository.save(new Genre(null, genreName)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<GenreDto> getAll() {
        return mapper.toDtos(genreRepository.findAll());
    }

}
