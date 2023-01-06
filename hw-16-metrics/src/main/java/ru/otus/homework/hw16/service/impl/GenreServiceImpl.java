package ru.otus.homework.hw16.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.hw16.dto.GenreDto;
import ru.otus.homework.hw16.entity.Genre;
import ru.otus.homework.hw16.mapper.GenreMapper;
import ru.otus.homework.hw16.repository.GenreRepository;
import ru.otus.homework.hw16.service.GenreService;

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
    public void delete(Long genreId) {
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
