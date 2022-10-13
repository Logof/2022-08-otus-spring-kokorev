package ru.otus.homework.repository.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.homework.entity.Author;
import ru.otus.homework.entity.Genre;
import ru.otus.homework.exception.DataNotFountException;
import ru.otus.homework.repository.GenreRepository;

import java.util.List;
import java.util.Map;

@Repository
public class GenreRepositoryImpl implements GenreRepository {
    private final NamedParameterJdbcOperations jdbc;

    public GenreRepositoryImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.jdbc = namedParameterJdbcOperations;
    }

    @Override
    public long count() {
        Long count = jdbc.queryForObject("SELECT count(1) FROM genres", Map.of(), Long.class);
        return count == null ? 0 : count;
    }

    @Override
    public int deleteById(long id) {
        return jdbc.update("DELETE genres WHERE id = :id", Map.of("id", id));
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query("SELECT id, genre_name FROM genres", new BeanPropertyRowMapper<>(Genre.class));
    }

    @Override
    public List<Genre> getGenresByIsbn(String isbn) {
        return jdbc.query("SELECT g.id, g.genre_name FROM genres g, assoc a" +
                        " WHERE a.external_id = g.id and a.external_class = :externalClass and a.isbn = :isbn",
                Map.of("isbn", isbn, "externalClass", Genre.class.getSimpleName()),
                new BeanPropertyRowMapper<>(Genre.class));
    }

    @Override
    public Genre getGenreById(Long id) {
        Genre genre = jdbc.queryForObject("SELECT id, genre_name FROM genres WHERE id = :id",
                Map.of("id", id), new BeanPropertyRowMapper<>(Genre.class));
        if (genre == null) {
            throw new DataNotFountException("Not found or too many values found");
        }
        return genre;
    }

    @Override
    public boolean isAttachedToBook(long id) {
        long countRow = jdbc.queryForObject("SELECT count(1) FROM genres g, assoc as" +
                        " WHERE as.external_id = g.id and as.external_class = :externalClass and g.id = :id",
                Map.of("id", id, "externalClass", Author.class.getSimpleName()), Long.class);
        return countRow > 0;
    }

    @Override
    public int insert(Genre object) {
        return jdbc.update("INSERT INTO genres (id, genre_name) VALUES (:id, :genre_name)",
                Map.of("id", object.getId(), "genre_name", object.getGenreName()));
    }

    @Override
    public int update(Genre object) {
        return jdbc.update("UPDATE genres set genre_name = :genre_name WHERE id = :id",
                Map.of("id", object.getId(), "genre_name", object.getGenreName()));
    }

    @Override
    public long generateId() {
        Long id = count() + 1;
        return id != null ? id : 0;
    }
}
