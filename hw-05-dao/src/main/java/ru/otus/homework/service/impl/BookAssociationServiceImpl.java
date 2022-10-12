package ru.otus.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.homework.dao.BookAssociationDao;
import ru.otus.homework.entity.BookAssociation;
import ru.otus.homework.exception.DataNotFountException;
import ru.otus.homework.exception.DeleteDataException;
import ru.otus.homework.service.BookAssociationService;

import static ru.otus.homework.dao.impl.BookAssociationDaoImpl.AUTHOR_CLASS_NAME;
import static ru.otus.homework.dao.impl.BookAssociationDaoImpl.GENRE_CLASS_NAME;

@Service
public class BookAssociationServiceImpl implements BookAssociationService {

    private final BookAssociationDao bookAssociationDao;

    public BookAssociationServiceImpl(BookAssociationDao bookAssociationDao) {
        this.bookAssociationDao = bookAssociationDao;
    }

    @Override
    public void addAuthorAssoc(String isbn, long authorId) {
        if (bookAssociationDao.isExist(new BookAssociation(isbn, authorId, AUTHOR_CLASS_NAME))) {
            throw new DataNotFountException("Record not found");
        } else {
            bookAssociationDao.insert(new BookAssociation(isbn, authorId, AUTHOR_CLASS_NAME));
        }
    }

    @Override
    public void addGenreAssoc(String isbn, long genreId) {
        if (bookAssociationDao.isExist(new BookAssociation(isbn, genreId, GENRE_CLASS_NAME))) {
            throw new DataNotFountException("Record not found");
        } else {
            bookAssociationDao.insert(new BookAssociation(isbn, genreId, GENRE_CLASS_NAME));
        }
    }

    @Override
    public void removeAuthor(String isbn, long authorId) {
        BookAssociation removedAssoc = new BookAssociation(isbn, authorId, AUTHOR_CLASS_NAME);
        if (bookAssociationDao.isExist(removedAssoc)) {
            if (bookAssociationDao.delete(removedAssoc) == 0) {
                throw new DeleteDataException("failed to delete row");
            }
        } else {
            throw new DataNotFountException("could not find row");
        }
    }

    @Override
    public void removeGenre(String isbn, long genreId) {
        BookAssociation removedAssoc = new BookAssociation(isbn, genreId, GENRE_CLASS_NAME);
        if (bookAssociationDao.isExist(removedAssoc)) {
            if (bookAssociationDao.delete(removedAssoc) == 0) {
                throw new DeleteDataException("failed to delete row");
            }
        } else {
            throw new DataNotFountException("could not find row");
        }
    }
}
