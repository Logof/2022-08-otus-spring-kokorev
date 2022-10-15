package ru.otus.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.homework.entity.Author;
import ru.otus.homework.exception.DeleteDataException;
import ru.otus.homework.repository.AuthorRepository;
import ru.otus.homework.service.AuthorService;
import ru.otus.homework.service.PrintService;

import java.util.List;

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
    public void delete(long authorId) {
        if (authorRepository.isAttachedToBook(authorId)) {
            throw new DeleteDataException("It' i's not possible to delete an entry while it has the possibility of an association");
        }
        authorRepository.delete(authorId);
        ioService.outString("Entry deleted");
    }

    @Override
    public Author findByAuthorFullName(String fullName) {
        return authorRepository.getByFullName(fullName);
    }

    @Override
    public Author add(String fullName) {
        Author author = authorRepository.insert(new Author(null, fullName));
        ioService.outString(String.format("Author added. ID: %d", author.getId()));
        return author;
    }

    @Override
    public void addToBook(String isbn, String fullName) {
        Author author = findByAuthorFullName(fullName);
        if (author == null) {
            author = add(fullName);
        }
        if (!authorRepository.isAttachedToBook(author.getId())) {
            authorRepository.createLinkToBook(isbn, author.getId());
        }
    }

    @Override
    public void outputAll() {
        List<Author> authors = authorRepository.getAll();
        ioService.outString(printService.objectsToPrint(authors));
    }
}
