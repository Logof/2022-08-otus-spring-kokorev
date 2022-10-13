package ru.otus.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.homework.entity.Genre;
import ru.otus.homework.exception.DataNotFountException;
import ru.otus.homework.exception.DeleteDataException;
import ru.otus.homework.repository.GenreDao;
import ru.otus.homework.service.GenreService;
import ru.otus.homework.service.PrintService;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;
    private final OutputServiceStreams ioService;

    private final PrintService<Genre> printService;

    public GenreServiceImpl(GenreDao genreDao,
                            OutputServiceStreams ioService,
                            PrintService<Genre> printService) {
        this.genreDao = genreDao;
        this.ioService = ioService;
        this.printService = printService;
    }

    @Override
    public void delete(long genreId) throws DeleteDataException {
        if (genreDao.isAttachedToBook(genreId)) {
            throw new DeleteDataException("It' i's not possible to delete an entry while it has the possibility of an association");
        }
        genreDao.delete(genreId);
        ioService.outString("Entry deleted");

    }

    @Override
    public void add(String genreName) {
        long id = genreDao.generateId();
        genreDao.insert(new Genre(id, genreName));
        ioService.outString(String.format("Genre added. ID: %d", id));
    }

    @Override
    public void outputAll() {
        List<Genre> genres = genreDao.getAll();
        ioService.outString(printService.objectsToPrint(genres));
    }

    @Override
    public void setDescription(long genreId, String description) {
        Genre genre = genreDao.getGenreById(genreId);
        if (genre == null) {
            throw new DataNotFountException(String.format("Genre with ID = %d not found", genreId));
        }
        genreDao.update(genre);
        ioService.outString(String.format("Genre description is set. ID: %d Description: %s", genreId, description));
    }
}
