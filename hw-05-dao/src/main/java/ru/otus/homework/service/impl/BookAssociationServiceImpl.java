package ru.otus.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.homework.dao.BookAssociationDao;
import ru.otus.homework.entity.BookAssociation;
import ru.otus.homework.exception.AuthorNotFountException;
import ru.otus.homework.exception.GenreNotFountException;
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
        if (bookAssociationDao.isExist(isbn, authorId, AUTHOR_CLASS_NAME)) {
            throw new AuthorNotFountException("Record not found");
        } else {
            bookAssociationDao.insert(new BookAssociation(isbn, authorId, AUTHOR_CLASS_NAME));
        }
    }

    @Override
    public void addGenreAssoc(String isbn, long genreId) {
        if (bookAssociationDao.isExist(isbn, genreId, GENRE_CLASS_NAME)) {
            throw new GenreNotFountException("Record not found");
        } else {
            bookAssociationDao.insert(new BookAssociation(isbn, genreId, GENRE_CLASS_NAME));
        }
    }

    @Override
    public void removeAuthor(String isbn, long authorId) {
        if (bookAssociationDao.isExist(isbn, authorId, AUTHOR_CLASS_NAME)) {
            bookAssociationDao.delete(isbn, authorId, AUTHOR_CLASS_NAME);
            //ioService.outputString("The author has been removed from the book. BookId: " + isbn + " AuthorId: " + authorId);
        } else {
            //TODO Вывести ошибку
            //ioService.outputString("The author with the ID=" + authorId + " is missing from the book ID: " + isbn);
        }
    }

    @Override
    public void removeGenre(String isbn, long genreId) {
        if (bookAssociationDao.isExist(isbn, genreId, GENRE_CLASS_NAME)) {
            bookAssociationDao.delete(isbn, genreId, GENRE_CLASS_NAME);
            //ioService.outputString("The genre has been removed from the book. BookId: " + isbn + " GenreId: " + genreId);
        } else {
            //TODO Вывести ошибку
            //ioService.outputString("The genre with the ID=" + genreId + " is missing from the book ID: " + isbn);
        }
    }
}
