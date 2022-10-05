package ru.otus.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.homework.dao.BookAssociationDao;
import ru.otus.homework.dao.BookDao;
import ru.otus.homework.entity.Book;
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
    public void add(String isbn, String title) {
        bookDao.insert(new Book(isbn, title));
        ioService.outputString("Book added. ISBN: " + isbn);
    }


    @Override
    public void delete(String isbn) {
        if (bookDao.getBookById(isbn) != null) {
            bookDao.delete(isbn);
            ioService.outputString("Book deleted. ID: " + isbn);
        }
    }

    @Override
    public void outputAll() {
        List<Book> books = bookDao.getAll();
        ioService.outputString("Total books: " + bookDao.count());
        for (Book book : books) {
            ioService.outputString(printService.objectToPrint(book));
        }
    }

    @Override
    public void output(String isbn) {
        ioService.outputString(printService.objectToPrint(bookDao.getBookById(isbn)));
    }

}
