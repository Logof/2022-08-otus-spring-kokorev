package ru.otus.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.homework.dao.BookAssociationDao;
import ru.otus.homework.dao.BookDao;
import ru.otus.homework.entity.Book;
import ru.otus.homework.exception.DataNotFountException;
import ru.otus.homework.service.BookService;
import ru.otus.homework.service.PrintService;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final BookAssociationDao bookAssociationDao;

    private final PrintService<Book> printService;

    private final OutputServiceStreams ioService;

    public BookServiceImpl(BookDao bookDao, BookAssociationDao bookAssociationDao,
                           PrintService<Book> printService, OutputServiceStreams ioService) {
        this.bookDao = bookDao;
        this.bookAssociationDao = bookAssociationDao;
        this.printService = printService;
        this.ioService = ioService;
    }

    @Override
    public void update(String isbn, Book book) {
        ioService.outputString(String.format("Updated %d book(s)", bookDao.update(isbn, book)));
    }

    @Override
    public void add(Book book) {
        int rowAddCount = bookDao.insert(book);
        ioService.outputString(rowAddCount == 1 ? String.format("Book added. ISBN: %s", book.getIsbn())
                : String.format("Added %d book(s) by ISBN %s ", rowAddCount, book.getIsbn()));
    }


    @Override
    public void deleteById(String isbn) {
        if (bookDao.getBookById(isbn) != null) {
            bookDao.delete(isbn);
            ioService.outputString("Book deleted. ID: " + isbn);
        } else {
            throw new DataNotFountException("");
        }
    }

    @Override
    public void getAll() {
        List<Book> books = bookDao.getAll();
        ioService.outputString("Total books: " + bookDao.count());
        for (Book book : books) {
            ioService.outputString(printService.objectToPrint(book));
        }
    }

    @Override
    public void getById(String isbn) {
        ioService.outputString(printService.objectToPrint(bookDao.getBookById(isbn)));
    }

}
