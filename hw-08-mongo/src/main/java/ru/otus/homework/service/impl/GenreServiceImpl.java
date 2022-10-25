package ru.otus.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.entity.Genre;
import ru.otus.homework.exception.DeleteDataException;
import ru.otus.homework.repository.GenreRepository;
import ru.otus.homework.service.GenreService;
import ru.otus.homework.service.print.PrintService;

import java.util.ArrayList;
import java.util.Optional;

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
    public void delete(String genreName) throws DeleteDataException {
        Genre genre = genreRepository.findById(genreName).orElse(null);

        if (genre != null && genre.getBookIsbns() != null && genre.getBookIsbns().size() > 0) {
            throw new DeleteDataException("It' i's not possible to delete an entry while it has the possibility of an association");
        }
        genreRepository.deleteById(genreName);
        ioService.outString("Entry deleted");

    }

    @Override
    @Transactional
    public Genre findOrCreate(String genreName) {
        Optional<Genre> genreOptional = genreRepository.findById(genreName);
        if (genreOptional.isPresent()) {
            return genreOptional.get();
        }
        Genre genre = genreRepository.save(new Genre(genreName));
        ioService.outString(String.format("Genre added. ID: %s", genre.getGenreName()));
        return genre;
    }

    @Override
    @Transactional
    public Genre save(Genre genre) {
        genreRepository.save(genre);
        ioService.outString(String.format("Genre added. ID: %s", genre.getGenreName()));
        return genre;
    }

    @Override
    @Transactional(readOnly = true)
    public void outputAll() {
        ioService.outString(printService.objectsToPrint(new ArrayList<>(genreRepository.findAll())));
    }

}
