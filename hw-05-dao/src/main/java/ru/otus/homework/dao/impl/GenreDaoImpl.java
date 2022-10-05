package ru.otus.homework.dao.impl;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.homework.dao.GenreDao;
import ru.otus.homework.entity.Genre;
import ru.otus.homework.exception.GenreNotFountException;
import ru.otus.homework.mapper.GenreMapper;

import java.util.List;
import java.util.Map;

@Repository
public class GenreDaoImpl implements GenreDao {
    private final NamedParameterJdbcOperations jdbc;

    public GenreDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.jdbc = namedParameterJdbcOperations;
    }

    @Override
    public long count() {
        Long count = jdbc.queryForObject("SELECT count(1) FROM genres",
                Map.of(), Long.class);
        return count == null ? 0 : count;
    }

    @Override
    public void delete(long id) {
        jdbc.update("DELETE genres WHERE id = :id",
                Map.of("id", id));
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query("SELECT id, genre_name FROM genres", Map.of(), new GenreMapper());
    }

    @Override
    public Genre getGenreById(Long id) {
        List<Genre> genres = jdbc.query("SELECT id, genre_name FROM genres WHERE id = :id",
                Map.of("id", id), new GenreMapper());
        if (genres.size() != 1) {
            throw new GenreNotFountException("Not found or too many values found");
        }
        return genres.get(0);
    }

    @Override
    public void insert(Genre object) {
        jdbc.update("INSERT INTO genres (id, genre_name) VALUES (:id, :genre_name)",
                Map.of("id", object.getId(), "genre_name", object.getGenreName()));
    }

    @Override
    public void update(Genre object) {
        jdbc.update("UPDATE genres set genre_name = :genre_name WHERE id = :id",
                Map.of("id", object.getId(), "genre_name", object.getGenreName()));
    }

    @Override
    public long generateId() {
        Long id = count() + 1;
        return id != null ? id : 0;
    }
}
