package ru.otus.homework.hw07.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.hw07.entity.Genre;
import ru.otus.homework.hw07.entity.dto.GenreDto;
import ru.otus.homework.hw07.mapper.GenreMapper;
import ru.otus.homework.hw07.repository.GenreRepository;
import ru.otus.homework.hw07.service.GenreService;
import ru.otus.homework.hw07.service.print.PrintService;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreMapper mapper;
    private final GenreRepository genreRepository;
    private final PrintService<GenreDto> printService;

    public GenreServiceImpl(GenreMapper mapper,
                            GenreRepository genreRepository,
                            PrintService<GenreDto> printService) {
        this.mapper = mapper;
        this.genreRepository = genreRepository;
        this.printService = printService;
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
