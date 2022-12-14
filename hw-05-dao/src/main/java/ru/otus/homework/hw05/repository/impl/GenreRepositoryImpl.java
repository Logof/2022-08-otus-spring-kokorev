package ru.otus.homework.hw05.repository.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.homework.hw05.entity.Genre;
import ru.otus.homework.hw05.exception.DataNotFountException;
import ru.otus.homework.hw05.repository.GenreRepository;

import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class GenreRepositoryImpl implements GenreRepository {
    private final NamedParameterJdbcOperations jdbc;

    private final GeneratedKeyHolder holder = new GeneratedKeyHolder();

    public GenreRepositoryImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.jdbc = namedParameterJdbcOperations;
    }

    @Override
    public void deleteById(long id) {
        jdbc.update("DELETE genres WHERE id = :id", Map.of("id", id));
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query("SELECT id, genre_name FROM genres", new BeanPropertyRowMapper<>(Genre.class));
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
    public Genre getGenreByName(String genreName) {
        List<Genre> genre =  jdbc.query("SELECT id, genre_name FROM genres WHERE genre_name = :genreName",
                Map.of("genreName", genreName), new BeanPropertyRowMapper<>(Genre.class));
        return genre.size() != 1 ? null : genre.get(0);
    }

    @Override
    public boolean genreHasBooks(long genreId) {
        long countRow = jdbc.queryForObject("SELECT count(1) FROM book_genres g WHERE g.genre_id = :genreId",
                Map.of("genreId", genreId), Long.class);
        return countRow > 0;
    }

    @Override
    public Genre insert(Genre object) {
        jdbc.update("INSERT INTO genres (genre_name) VALUES (:genreName)",
                new MapSqlParameterSource("genreName", object.getGenreName()),
                holder);
        object.setId(holder.getKey().longValue());
        return object;
    }

    @Override
    public int update(Genre object) {
        return jdbc.update("UPDATE genres set genre_name = :genre_name WHERE id = :id",
                Map.of("id", object.getId(), "genre_name", object.getGenreName()));
    }


}
