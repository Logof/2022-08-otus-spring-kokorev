package ru.otus.homework.hw06.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.hw06.entity.Author;
import ru.otus.homework.hw06.repository.AuthorRepository;
import ru.otus.homework.hw06.service.AuthorService;
import ru.otus.homework.hw06.service.print.PrintService;

import java.util.Set;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final PrintService<Author> printService;
    private final AuthorRepository authorRepository;
    private final OutputServiceStreams ioService;

    public AuthorServiceImpl(PrintService<Author> printService,
                             AuthorRepository authorRepository,
                             OutputServiceStreams ioService) {
        this.printService = printService;
        this.authorRepository = authorRepository;
        this.ioService = ioService;
    }

    @Override
    @Transactional
    public void delete(long authorId) {
        authorRepository.delete(authorId);
        ioService.outString("Entry deleted");
    }

    @Override
    @Transactional
    public Author add(String fullName) {
        Author author = authorRepository.save(new Author(fullName));
        ioService.outString(String.format("Author added. ID: %d", author.getId()));
        return author;
    }

    @Override
    @Transactional(readOnly = true)
    public void outputAll() {
        Set<Author> authors = authorRepository.getAll();
        ioService.outString(printService.objectsToPrint(authors));
    }
}
