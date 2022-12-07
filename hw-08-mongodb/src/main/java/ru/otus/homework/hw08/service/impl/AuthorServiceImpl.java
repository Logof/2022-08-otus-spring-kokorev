package ru.otus.homework.hw08.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.hw08.entity.Author;
import ru.otus.homework.hw08.exception.DeleteDataException;
import ru.otus.homework.hw08.repository.AuthorRepository;
import ru.otus.homework.hw08.service.AuthorService;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional
    public void delete(String fullName) throws DeleteDataException {
        Author author = authorRepository.findByFullName(fullName);
        if (author.getBookList().size() != 0) {
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
