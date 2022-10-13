package ru.otus.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.homework.entity.Author;
import ru.otus.homework.exception.DeleteDataException;
import ru.otus.homework.repository.AuthorDao;
import ru.otus.homework.service.AuthorService;
import ru.otus.homework.service.PrintService;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final PrintService<Author> printService;

    private final AuthorDao authorDao;
    private final OutputServiceStreams ioService;

    public AuthorServiceImpl(PrintService<Author> printService,
                             AuthorDao authorDao,
                             OutputServiceStreams ioService) {
        this.printService = printService;
        this.authorDao = authorDao;
        this.ioService = ioService;
    }

    @Override
    public void delete(long authorId) {
        if (authorDao.isAttachedToBook(authorId)) {
            throw new DeleteDataException("It' i's not possible to delete an entry while it has the possibility of an association");
        }
        authorDao.delete(authorId);
        ioService.outString("Entry deleted");
    }

    @Override
    public void add(String fullName) {
        long id = authorDao.generateId();
        authorDao.insert(new Author(id, fullName));
        ioService.outString(String.format("Author added. ID: %d", id));
    }

    @Override
    public void outputAll() {
        List<Author> authors = authorDao.getAll();
        ioService.outString(printService.objectsToPrint(authors));
    }
}
