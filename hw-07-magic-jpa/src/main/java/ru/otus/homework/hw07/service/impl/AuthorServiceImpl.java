package ru.otus.homework.hw07.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.hw07.entity.Author;
import ru.otus.homework.hw07.repository.AuthorRepository;
import ru.otus.homework.hw07.service.AuthorService;
import ru.otus.homework.hw07.service.print.PrintService;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final PrintService<Author> printService;
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(PrintService<Author> printService,
                             AuthorRepository authorRepository) {
        this.printService = printService;
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional
    public void delete(long authorId) {
        authorRepository.deleteById(authorId);
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
