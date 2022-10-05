package ru.otus.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.homework.dao.BookAssociationDao;
import ru.otus.homework.dao.GenreDao;
import ru.otus.homework.entity.Genre;
import ru.otus.homework.exception.DeleteRowException;
import ru.otus.homework.service.GenreService;
import ru.otus.homework.service.PrintService;

import java.util.List;

import static ru.otus.homework.dao.impl.BookAssociationDaoImpl.GENRE_CLASS_NAME;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;
    private final BookAssociationDao bookAssociationDao;
    private final OutputServiceStreams ioService;

    private final PrintService<Genre> printService;

    public GenreServiceImpl(GenreDao genreDao, BookAssociationDao bookAssociationDao,
                            OutputServiceStreams ioService, PrintService<Genre> printService) {
        this.genreDao = genreDao;
        this.bookAssociationDao = bookAssociationDao;
        this.ioService = ioService;
        this.printService = printService;
    }

    @Override
    public void delete(long genreId) {
        if (bookAssociationDao.isExistExternalLink(genreId, GENRE_CLASS_NAME)) {
            throw new DeleteRowException("It' i's not possible to delete an entry while it has the possibility of an association");
        } else {
            genreDao.delete(genreId);
            ioService.outputString("Entry deleted");
        }
    }

    @Override
    public void add(String genreName) {
        long id = genreDao.generateId();
        genreDao.insert(new Genre(id, genreName));
        ioService.outputString("Genre added. ID: " + id);
    }

    @Override
    public void outputAll() {
        List<Genre> genres = genreDao.getAll();
        ioService.outputString("Total genres: " + genreDao.count());
        ioService.outputString(printService.objectsToPrint(genres));
    }

    @Override
    public void setDescription(long genreId, String description) {
        Genre genre = genreDao.getGenreById(genreId);
        if (genre != null) {
            genreDao.update(genre);
            ioService.outputString("Genre description is set. ID: " + genreId + " Description: " + description);
        }
    }

}
