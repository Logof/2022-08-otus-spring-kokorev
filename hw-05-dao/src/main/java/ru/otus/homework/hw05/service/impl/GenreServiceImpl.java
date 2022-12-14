package ru.otus.homework.hw05.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.homework.hw05.entity.Genre;
import ru.otus.homework.hw05.exception.DeleteDataException;
import ru.otus.homework.hw05.repository.GenreRepository;
import ru.otus.homework.hw05.service.GenreService;
import ru.otus.homework.hw05.service.print.PrintService;

import java.util.List;

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
    public void delete(long genreId) throws DeleteDataException {
        if (genreRepository.genreHasBooks(genreId)) {
            throw new DeleteDataException("It' i's not possible to delete an entry while it has the possibility of an association");
        }
        genreRepository.deleteById(genreId);
        ioService.outString("Entry deleted");

    }

    @Override
    public Genre add(String genreName) {
        Genre genre = genreRepository.insert(new Genre(null, genreName));
        ioService.outString(String.format("Genre added. ID: %d", genre.getId()));
        return genre;
    }

    /*@Override
    public Genre findByGenreName(String genreName) {
        return genreRepository.getGenreByName(genreName);
    }*/

    @Override
    public void outputAll() {
        List<Genre> genres = genreRepository.getAll();
        ioService.outString(printService.objectsToPrint(genres));
    }

}
