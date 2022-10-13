package ru.otus.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.homework.entity.Author;
import ru.otus.homework.entity.Book;
import ru.otus.homework.repositories.AuthorRepository;
import ru.otus.homework.service.AuthorService;

import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository repository;

    public AuthorServiceImpl(AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    public void deleteAuthorFromBook(Book book, long authorId) {
        Optional<Author> author = repository.findById(authorId);
        if (author.isPresent()) {
            book.getAuthors().remove(author.get());
        }
    }

    @Override
    public void deleteAuthor(long authorId) {
        repository.deleteById(authorId);
    }

    @Override
    public void addAuthorToBook(Book book, Author author) {
        if (author.getId() == null) {
            author = repository.save(author);
        } else {
            Optional<Author> foundAuthor = repository.findById(author.getId());
            if (foundAuthor.isEmpty()) {
                author = repository.save(author);
            }
        }
        book.getAuthors().add(author);
    }

    @Override
    public void addAuthor(String fullName) {
        repository.save(new Author(null, fullName));
    }

    @Override
    public void outputAll() {
        repository.findAll();
    }
}
