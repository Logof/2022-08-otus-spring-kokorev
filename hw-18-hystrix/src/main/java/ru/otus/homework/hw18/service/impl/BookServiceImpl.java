package ru.otus.homework.hw18.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.hw18.dto.BookDto;
import ru.otus.homework.hw18.entity.Book;
import ru.otus.homework.hw18.exception.DataNotFountException;
import ru.otus.homework.hw18.exception.FieldRequiredException;
import ru.otus.homework.hw18.mapper.BookMapper;
import ru.otus.homework.hw18.repository.BookRepository;
import ru.otus.homework.hw18.security.CustomRolesPermission;
import ru.otus.homework.hw18.service.BookService;
import ru.otus.homework.hw18.service.PermissionService;

import java.util.List;
import java.util.Objects;

@Slf4j
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
        if (Objects.isNull(book) || Objects.isNull(book.getId()) || book.getTitle().isBlank()) {
            throw new FieldRequiredException("isbn", "title");
        }
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

    //@PreAuthorize("hasPermission(#id, 'ru.otus.homework.hw16.entity.Book', 'READ')")
    @Override
    @Transactional(readOnly = true)
    public BookDto getByIsbn(Long id) {
        Book result = bookRepository.findById(id).orElseThrow(() -> new DataNotFountException("Book not found"));
        BookDto resultDto = bookMapper.toDto(result);
        return resultDto;
    }

    @PreAuthorize("hasPermission(#id, 'ru.otus.homework.hw16.entity.Book', 'DELETE')")
    @Override
    @Transactional
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
