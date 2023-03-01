package ru.otus.homework.hw18.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.hw18.dto.GenreDto;
import ru.otus.homework.hw18.entity.Genre;
import ru.otus.homework.hw18.exception.FieldRequiredException;
import ru.otus.homework.hw18.mapper.GenreMapper;
import ru.otus.homework.hw18.repository.GenreRepository;
import ru.otus.homework.hw18.service.GenreService;

import java.util.List;
import java.util.Objects;

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
    public void delete(Long genreId) {
        genreRepository.deleteById(genreId);
    }

    @Override
    @Transactional
    public GenreDto add(String genreName) {
        if (Objects.isNull(genreName) || genreName.isBlank()) {
            throw new FieldRequiredException("genreName");
        }
        return mapper.toDto(genreRepository.save(new Genre(null, genreName)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<GenreDto> getAll() {
        return mapper.toDtos(genreRepository.findAll());
    }

}
