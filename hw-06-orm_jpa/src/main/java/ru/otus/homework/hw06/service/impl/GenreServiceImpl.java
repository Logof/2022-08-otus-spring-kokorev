package ru.otus.homework.hw06.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.hw06.entity.Genre;
import ru.otus.homework.hw06.exception.DeleteDataException;
import ru.otus.homework.hw06.repository.GenreRepository;
import ru.otus.homework.hw06.service.GenreService;
import ru.otus.homework.hw06.service.print.PrintService;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final OutputServiceStreams ioService;

    private final PrintService<Genre> printService;

    public GenreServiceImpl(GenreRepository genreRepository,
                            OutputServiceStreams ioService,
                            PrintService<Genre> printService) {
        this.genreRepository = genreRepository;
        this.ioService = ioService;
        this.printService = printService;
    }

    @Override
    @Transactional
    public void delete(long genreId) throws DeleteDataException {
        if (genreRepository.genreHasBooks(genreId)) {
            throw new DeleteDataException("It' i's not possible to delete an entry while it has the possibility of an association");
        }
        genreRepository.deleteById(genreId);
        ioService.outString("Entry deleted");

    }

    @Override
    @Transactional
    public void add(String genreName) {
        Genre genre = genreRepository.save(new Genre(genreName));
        ioService.outString(String.format("Genre added. ID: %d", genre.getId()));
    }

    @Override
    @Transactional(readOnly = true)
    public void outputAll() {
        ioService.outString(printService.objectsToPrint(genreRepository.getAll()));
    }

}
