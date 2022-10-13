package ru.otus.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.homework.entity.Book;
import ru.otus.homework.exception.DataNotFountException;
import ru.otus.homework.repository.BookRepository;
import ru.otus.homework.service.BookService;
import ru.otus.homework.service.PrintService;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final PrintService<Book> printService;

    private final OutputServiceStreams ioService;

    public BookServiceImpl(BookRepository bookRepository, PrintService<Book> printService, OutputServiceStreams ioService) {
        this.bookRepository = bookRepository;
        this.printService = printService;
        this.ioService = ioService;
    }

    @Override
    public void update(Book book) {
        ioService.outString(String.format("Updated %d book(s)", bookRepository.update(book)));
    }

    @Override
    public void add(Book book) {
        int rowAddCount = bookRepository.insert(book);
        ioService.outString(rowAddCount == 1 ? String.format("Book added. ISBN: %s", book.getIsbn())
                : String.format("Added %d book(s) by ISBN %s ", rowAddCount, book.getIsbn()));
    }


    @Override
    public void deleteById(String isbn) {
        if (bookRepository.getBookById(isbn) == null) {
            throw new DataNotFountException(String.format("Book with ISBN %s not found", isbn));
        }
        bookRepository.delete(isbn);
        ioService.outString(String.format("Book deleted. ID: %s", isbn));
    }

    @Override
    public void getAll() {
        List<Book> books = bookRepository.getAll();
        ioService.outString(printService.objectsToPrint(books));
    }

    @Override
    public void getById(String isbn) {
        ioService.outString(printService.objectToPrint(bookRepository.getBookById(isbn)));
    }

}
