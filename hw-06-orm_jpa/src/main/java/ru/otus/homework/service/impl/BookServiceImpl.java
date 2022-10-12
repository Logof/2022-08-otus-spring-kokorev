package ru.otus.homework.service.impl;

import ru.otus.homework.entity.Book;
import ru.otus.homework.exception.DataNotFountException;
import ru.otus.homework.repositories.BookRepository;
import ru.otus.homework.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addNewBook(Book book) {
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
        List<Book> books = repository.findAll();
        //ioService.outString(printService.objectsToPrint(books));
    }

    @Override
    public void getById(String isbn) {
        //ioService.outString(printService.objectToPrint(
        repository.findByIsbn(isbn);
    }

}
