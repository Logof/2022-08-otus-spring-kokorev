package ru.otus.homework.hw13.service.impl;

import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.otus.homework.hw13.entity.Book;
import ru.otus.homework.hw13.exception.DataNotFountException;
import ru.otus.homework.hw13.repository.BookRepository;
import ru.otus.homework.hw13.service.BookService;
import ru.otus.homework.hw13.service.PermissionService;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    private final PermissionService permissionService;

    public BookServiceImpl(BookRepository bookRepository,
                           PermissionService permissionService) {
        this.bookRepository = bookRepository;
        this.permissionService = permissionService;
    }


    @Override
    public Book save(Book book, Authentication authentication) {

        Book savedBook = bookRepository.save(book);
        permissionService.addPermissionForUser(book, BasePermission.WRITE, authentication.getName());
        permissionService.addPermissionForUser(book, BasePermission.READ, authentication.getName());

        return savedBook;
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book getByIsbn(long isbn) {
        return bookRepository.findById(isbn).orElseThrow(() -> new DataNotFountException("Book not found"));
    }

    @Override
    public void deleteById(long isbn) {
        bookRepository.deleteById(isbn);
    }
}
