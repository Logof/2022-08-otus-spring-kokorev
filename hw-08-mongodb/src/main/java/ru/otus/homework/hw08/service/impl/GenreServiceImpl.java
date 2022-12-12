package ru.otus.homework.hw08.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.hw08.entity.Book;
import ru.otus.homework.hw08.entity.Genre;
import ru.otus.homework.hw08.exception.DataNotFountException;
import ru.otus.homework.hw08.exception.DeleteDataException;
import ru.otus.homework.hw08.repository.BookRepository;
import ru.otus.homework.hw08.repository.GenreRepository;
import ru.otus.homework.hw08.service.GenreService;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    private final BookRepository bookRepository;

    public GenreServiceImpl(GenreRepository genreRepository, BookRepository bookRepository) {
        this.genreRepository = genreRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public void delete(String genreName) throws DeleteDataException {
        Genre genre = genreRepository.findByGenreNameLike(genreName)
                .orElseThrow(() -> new DataNotFountException("Genre not found"));
        List<Book> book = bookRepository.findAllByGenres(genre);
        if (book.size() != 0) {
            throw new DeleteDataException("Can't remove genre - there are links to books");
        }
        genreRepository.deleteById(genre.getId());
    }

    @Override
    @Transactional
    public Genre add(String genreName) {
        return genreRepository.save(new Genre(genreName));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

}
