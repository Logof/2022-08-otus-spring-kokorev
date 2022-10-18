package ru.otus.homework.service;

import org.springframework.stereotype.Service;
import ru.otus.homework.entity.Book;
import ru.otus.homework.repository.BookRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional(readOnly = false)
    public void getAll() {
        bookRepository.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public void getById(String isbn) {
        bookRepository.getByIsbn(isbn);
    }


    @Override
    @Transactional
    public void add(Book book) {
        if (book == null || book.getIsbn() == null || book.getIsbn().isBlank() || book.getTitle() == null || book.getTitle().isBlank()) {
            //todo Заменить на throw
            //ioService.outString("");
        }
        bookRepository.save(book);
    }


}
