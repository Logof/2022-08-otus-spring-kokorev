package ru.otus.homework.repository.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.homework.entity.Author;
import ru.otus.homework.repository.AuthorRepository;

import java.util.List;
import java.util.Map;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {
    private final NamedParameterJdbcOperations jdbc;

    public AuthorRepositoryImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.jdbc = namedParameterJdbcOperations;
    }

    @Override
    public long count() {
        return jdbc.queryForObject("SELECT count(1) FROM authors",
                Map.of(), Long.class);
    }

    @Override
    public Author getAuthorById(long id) {
        return jdbc.queryForObject("SELECT id, full_name FROM authors WHERE id = :id",
                Map.of("id", id), new BeanPropertyRowMapper<>(Author.class));
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
        return jdbc.queryForList("SELECT a.id, a.full_name FROM authors a, assoc s" +
                        " WHERE s.external_id = a.id and s.external_class = :externalClass and s.isbn = :isbn",
                Map.of("isbn", isbn, "externalClass", Author.class.getSimpleName()), Author.class);
    }

    @Override
    public boolean isAttachedToBook(long id) {
        long countRow = jdbc.queryForObject("SELECT count(1) FROM authors a, assoc s" +
                        " WHERE s.external_id = a.id and s.external_class = :externalClass and a.id = :id",
                Map.of("id", id, "externalClass", Author.class.getSimpleName()), Long.class);
        return countRow > 0;
    }

    @Override
    public int insert(Author object) {
        return jdbc.update("INSERT INTO authors (id, full_name) VALUES (:id, :full_name)",
                Map.of("id", object.getId(),
                        "full_name", object.getFullName()));
    }

    @Override
    public int update(Author object) {
        return jdbc.update("UPDATE authors set full_name = :full_name WHERE id = :id",
                Map.of("id", object.getId(),
                        "full_name", object.getFullName()));
    }

    @Override
    public long generateId() {
        return count() + 1;
    }
}
