package ru.otus.homework.hw08.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.hw08.entity.Author;
import ru.otus.homework.hw08.exception.DataNotFountException;
import ru.otus.homework.hw08.exception.DeleteDataException;
import ru.otus.homework.hw08.repository.AuthorRepository;
import ru.otus.homework.hw08.service.AuthorService;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional
    public void delete(String fullName) {
        Author author = authorRepository.findById(fullName).orElseThrow(() -> new DataNotFountException("Author not found"));
        if (author.getIsbnList().size() != 0) {
            throw new DeleteDataException("Can't remove author - there are links to books");
        }
        authorRepository.deleteById(fullName);
    }

    @Override
    @Transactional
    public Author add(String fullName) {
        return authorRepository.save(new Author(fullName, new ArrayList<>()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAll() {
        return authorRepository.findAll();
    }
}
