package ru.otus.homework.repository.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.homework.entity.Author;
import ru.otus.homework.repository.AuthorRepository;

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
    public List<Author> getAuthorsByIsbn(String isbn) {
        return jdbc.query("" +
                        "SELECT a.id, a.full_name " +
                        "  FROM authors a, book_authors s" +
                        " WHERE s.author_id = a.id and s.isbn = :isbn",
                Map.of("isbn", isbn),
                new BeanPropertyRowMapper<>(Author.class));
    }

    @Override
    public boolean isAttachedToBook(long id) {
        long countRow = jdbc.queryForObject("SELECT count(1) FROM book_authors s WHERE s.author_id = :id",
                Map.of("id", id), Long.class);
        return countRow > 0;
    }

    @Override
    public Author insert(Author object) {
        if (object == null || object.getFullName() == null || object.getFullName().isBlank()) {
            return null;
        }
        jdbc.update("INSERT INTO authors (full_name) VALUES (:fullName)",
                new MapSqlParameterSource("fullName", object.getFullName()),
                holder);
        object.setId(holder.getKey().longValue());
        return object;
    }

    @Override
    public int update(Author object) {
        if (object == null) {
            return 0;
        }
        return jdbc.update("UPDATE authors set full_name = :full_name WHERE id = :id",
                Map.of("id", object.getId(),
                        "full_name", object.getFullName()));
    }


}
