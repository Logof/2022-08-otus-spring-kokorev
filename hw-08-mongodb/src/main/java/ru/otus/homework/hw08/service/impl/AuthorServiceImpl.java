package ru.otus.homework.hw08.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.hw08.entity.Author;
import ru.otus.homework.hw08.exception.DataNotFountException;
import ru.otus.homework.hw08.exception.DeleteDataException;
import ru.otus.homework.hw08.repository.AuthorRepository;
import ru.otus.homework.hw08.repository.BookRepository;
import ru.otus.homework.hw08.service.AuthorService;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository,
                             BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public void delete(String fullName) throws DeleteDataException {
        Author author = authorRepository.findByFullName(fullName)
                .orElseThrow(() -> new DataNotFountException("Genre not found"));
        if (bookRepository.existsByAuthors(author)) {
            throw new DeleteDataException("Can't remove author - there are links to books");
        }
        authorRepository.deleteById(author.getId());
    }

    @Override
    @Transactional
    public Author add(String fullName) {
        return authorRepository.save(new Author(fullName));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAll() {
        return authorRepository.findAll();
    }
}
