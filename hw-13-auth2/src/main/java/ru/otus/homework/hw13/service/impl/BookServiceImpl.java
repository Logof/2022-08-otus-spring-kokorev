package ru.otus.homework.hw13.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.otus.homework.hw13.dto.BookDto;
import ru.otus.homework.hw13.entity.Book;
import ru.otus.homework.hw13.exception.DataNotFountException;
import ru.otus.homework.hw13.mapper.BookMapper;
import ru.otus.homework.hw13.repository.BookRepository;
import ru.otus.homework.hw13.security.CustomRolesPermission;
import ru.otus.homework.hw13.service.BookService;
import ru.otus.homework.hw13.service.PermissionService;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    private final PermissionService<Book> permissionService;

    public BookServiceImpl(BookRepository bookRepository,
                           BookMapper bookMapper,
                           PermissionService<Book> permissionService) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.permissionService = permissionService;
    }


    @Override
    public Book save(Book book, Authentication authentication) {
        Book savedBook = bookRepository.save(book);
        permissionService.addPermissionForRole(book, CustomRolesPermission.ROLE_READER, authentication.getName());
        permissionService.addPermissionForUser(book, CustomRolesPermission.ROLE_EDITOR, authentication.getName());
        return savedBook;
    }

    @Override
    public List<BookDto> getAll() {
        return bookMapper.toDtos(bookRepository.findAll());
    }

    @Override
    public BookDto getByIsbn(long isbn) {
        return bookMapper.toDto(bookRepository.findById(isbn)
                .orElseThrow(() -> new DataNotFountException("Book not found")));
    }

    @Override
    public void deleteById(long isbn) {
        bookRepository.deleteById(isbn);
    }
}
