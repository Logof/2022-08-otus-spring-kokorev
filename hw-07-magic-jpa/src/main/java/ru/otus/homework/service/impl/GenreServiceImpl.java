package ru.otus.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.entity.Genre;
import ru.otus.homework.exception.DeleteDataException;
import ru.otus.homework.repository.GenreRepository;
import ru.otus.homework.service.GenreService;
import ru.otus.homework.service.print.PrintService;

import java.util.HashSet;

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
        if (genreRepository.isAttachedToBook(genreId)) {
            throw new DeleteDataException("It' i's not possible to delete an entry while it has the possibility of an association");
        }
        genreRepository.deleteById(genreId);
        ioService.outString("Entry deleted");

    }

    @Override
    @Transactional
    public void add(String genreName) {
        Genre genre = genreRepository.saveAndFlush(new Genre(genreName));
        ioService.outString(String.format("Genre added. ID: %d", genre.getId()));
    }

    @Override
    @Transactional(readOnly = true)
    public void outputAll() {
        ioService.outString(printService.objectsToPrint(new HashSet<>(genreRepository.findAll())));
    }

}
