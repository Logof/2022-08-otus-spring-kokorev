package ru.otus.homework.hw05.repository.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.homework.hw05.entity.Author;
import ru.otus.homework.hw05.repository.AuthorRepository;

import java.util.List;
import java.util.Map;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {
    private final NamedParameterJdbcOperations jdbc;

    private final GeneratedKeyHolder holder = new GeneratedKeyHolder();

    public AuthorRepositoryImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.jdbc = namedParameterJdbcOperations;
    }

    @Override
    public Author getAuthorById(long id) {
        return jdbc.queryForObject("SELECT id, full_name FROM authors WHERE id = :id",
                Map.of("id", id), new BeanPropertyRowMapper<>(Author.class));
    }

    @Override
    public Author getByFullName(String fullName) {
        List<Author> author = jdbc.query("SELECT id, full_name FROM authors WHERE full_name = :fullName",
                Map.of("fullName", fullName), new BeanPropertyRowMapper<>(Author.class));
        return author.size() != 1  ? null : author.get(0);
    }


    @Override
    public void delete(long id) {
        jdbc.update("DELETE authors WHERE id = :id", Map.of("id", id));
    }


    @Override
    public List<Author> getAll() {
        return jdbc.query("SELECT id, full_name FROM authors", Map.of(), new BeanPropertyRowMapper<>(Author.class));
    }

    @Override
    public boolean authorHasBooks(long id) {
        long countRow = jdbc.queryForObject("SELECT count(1) FROM book_authors s WHERE s.author_id = :id",
                Map.of("id", id), Long.class);
        return countRow > 0;
    }

    @Override
    public Author insert(Author object) {
        jdbc.update("INSERT INTO authors (full_name) VALUES (:fullName)",
                new MapSqlParameterSource("fullName", object.getFullName()),
                holder);
        object.setId(holder.getKey().longValue());
        return object;
    }

    @Override
    public int update(Author object) {
        return jdbc.update("UPDATE authors set full_name = :full_name WHERE id = :id",
                Map.of("id", object.getId(),
                        "full_name", object.getFullName()));
    }


}
