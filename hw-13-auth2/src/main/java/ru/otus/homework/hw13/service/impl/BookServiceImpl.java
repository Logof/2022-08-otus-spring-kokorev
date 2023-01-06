package ru.otus.homework.hw13.service.impl;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public Book save(Book book, Authentication authentication) {
        Book savedBook = bookRepository.save(book);
        permissionService.addPermissionForRole(book, CustomRolesPermission.ROLE_READER, authentication.getName());
        permissionService.addPermissionForRole(book, CustomRolesPermission.ROLE_EDITOR, authentication.getName());
        return savedBook;
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDto> getAll() {
        return bookMapper.toDtos(bookRepository.findAll());
    }

    @PreAuthorize("hasPermission(#id, 'ru.otus.homework.hw13.entity.Book', 'READ')")
    @Override
    @Transactional(readOnly = true)
    public BookDto getByIsbn(Long id) {
        return bookMapper.toDto(bookRepository.findById(id)
                .orElseThrow(() -> new DataNotFountException("Book not found")));
    }

    @PreAuthorize("hasPermission(#id, 'ru.otus.homework.hw13.entity.Book', 'DELETE')")
    @Override
    @Transactional
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
