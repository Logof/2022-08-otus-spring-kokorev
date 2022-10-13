package ru.otus.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.homework.entity.Book;
import ru.otus.homework.exception.DataNotFountException;
import ru.otus.homework.repositories.BookRepository;
import ru.otus.homework.service.BookService;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    private final BookPrintService printService;

    public BookServiceImpl(BookRepository repository, BookPrintService printService) {
        this.repository = repository;
        this.printService = printService;
    }

    @Override
    public void saveBook(Book book) {
        repository.save(book);
    }

    @Override
    public void deleteByIsbn(String isbn) {
        if (repository.findByIsbn(isbn).isEmpty()) {
            throw new DataNotFountException(String.format("Book with ISBN %s not found", isbn));
        }
        repository.deleteByIsbn(isbn);
        //ioService.outString(String.format("Book deleted. ID: %s", isbn));
    }

    @Override
    public void getAll() {
        printService.objectsToPrint(repository.findAll());
        //ioService.outString(printService.objectsToPrint(books));
    }

    @Override
    public void getByIsbn(String isbn) {
        //ioService.outString(printService.objectToPrint(
        Optional<Book> optionalBook = repository.findByIsbn(isbn);
        if (optionalBook.isPresent()) {
            printService.objectToPrint(optionalBook.get());
        }
    }

}
