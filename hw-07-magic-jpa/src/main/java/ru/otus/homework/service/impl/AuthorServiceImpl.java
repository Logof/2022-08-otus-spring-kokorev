package ru.otus.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.entity.Author;
import ru.otus.homework.exception.DeleteDataException;
import ru.otus.homework.repository.AuthorRepository;
import ru.otus.homework.service.AuthorService;
import ru.otus.homework.service.print.PrintService;

import java.util.HashSet;
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
        if (authorRepository.isAttachedToBook(authorId)) {
            throw new DeleteDataException("It' i's not possible to delete an entry while it has the possibility of an association");
        }
        authorRepository.deleteById(authorId);
        ioService.outString("Entry deleted");
    }

    @Override
    @Transactional
    public Author add(String fullName) {
        Author author = authorRepository.saveAndFlush(new Author(fullName));
        ioService.outString(String.format("Author added. ID: %d", author.getId()));
        return author;
    }

    @Override
    @Transactional(readOnly = true)
    public void outputAll() {
        //TODO
        Set<Author> authors = new HashSet<>(authorRepository.findAll());
        ioService.outString(printService.objectsToPrint(authors));
    }
}
