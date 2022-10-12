package ru.otus.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.homework.dao.BookAssociationDao;
import ru.otus.homework.dao.GenreDao;
import ru.otus.homework.entity.BookAssociation;
import ru.otus.homework.entity.Genre;
import ru.otus.homework.exception.DataNotFountException;
import ru.otus.homework.exception.DeleteDataException;
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
    public void delete(long genreId) throws DeleteDataException {
        if (bookAssociationDao.isExist(new BookAssociation(null, genreId, GENRE_CLASS_NAME))) {
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
