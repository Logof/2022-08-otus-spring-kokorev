package ru.otus.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.entity.Author;
import ru.otus.homework.exception.DataNotFountException;
import ru.otus.homework.exception.DeleteDataException;
import ru.otus.homework.repository.AuthorRepository;
import ru.otus.homework.service.AuthorService;
import ru.otus.homework.service.print.PrintService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public void delete(String fullName) {
        Author author = authorRepository.findById(fullName).orElse(null);

        if (author == null) {
            throw new DataNotFountException("Data not found");
        }
        if (author != null && author.getBookIsbns() != null && author.getBookIsbns().size() > 0) {
            throw new DeleteDataException("It' i's not possible to delete an entry while it has the possibility of an association");
        }
        authorRepository.deleteById(fullName);
        ioService.outString("Entry deleted");
    }

    @Override
    @Transactional
    public Author findOrCreate(String fullName) {
        Optional<Author> authorOptional = authorRepository.findById(fullName);
        if (authorOptional.isPresent()) {
            return authorOptional.get();
        }
        Author author = authorRepository.save(new Author(fullName));
        ioService.outString(String.format("Author added. ID: %s", author.getFullName()));
        return author;
    }

    @Override
    @Transactional
    public Author save(Author author) {
        author = authorRepository.save(author);
        ioService.outString(String.format("Author added. ID: %s", author.getFullName()));
        return author;
    }


    @Override
    @Transactional(readOnly = true)
    public void outputAll() {
        //TODO
        List<Author> authors = new ArrayList<>(authorRepository.findAll());
        ioService.outString(printService.objectsToPrint(authors));
    }
}
