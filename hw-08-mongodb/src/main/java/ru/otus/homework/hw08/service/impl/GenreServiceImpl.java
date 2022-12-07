package ru.otus.homework.hw08.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.hw08.entity.Genre;
import ru.otus.homework.hw08.exception.DataNotFountException;
import ru.otus.homework.hw08.exception.DeleteDataException;
import ru.otus.homework.hw08.repository.GenreRepository;
import ru.otus.homework.hw08.service.GenreService;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    @Transactional
    public void delete(String genreName) throws DeleteDataException {
        Genre genre = genreRepository.findById(genreName).orElseThrow(() -> new DataNotFountException("Genre not found"));
        if (genre.getBookList().size() != 0) {
            throw new DeleteDataException("Can't remove genre - there are links to books");
        }
        genreRepository.deleteById(genreName);
    }

    @Override
    @Transactional
    public Genre add(String genreName) {
        return genreRepository.save(new Genre(genreName, new ArrayList<>()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

}
