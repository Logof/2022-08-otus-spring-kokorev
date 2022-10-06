package ru.otus.homework.dao.impl;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.homework.dao.AuthorDao;
import ru.otus.homework.entity.Author;
import ru.otus.homework.exception.DataNotFountException;
import ru.otus.homework.mapper.AuthorMapper;

import java.util.List;
import java.util.Map;

@Repository
public class AuthorDaoImpl implements AuthorDao {
    private final NamedParameterJdbcOperations jdbc;

    public AuthorDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.jdbc = namedParameterJdbcOperations;
    }

    @Override
    public long count() {
        Long count = jdbc.queryForObject("SELECT count(1) FROM authors",
                Map.of(), Long.class);
        return count == null ? 0 : count;
    }

    @Override
    public Author getAuthorById(Long id) {
        List<Author> authors = jdbc.query("SELECT id, full_name FROM authors WHERE id = :id",
                Map.of("id", id), new AuthorMapper());
        if (authors.size() != 1) {
            throw new DataNotFountException("Not found or too many values found");
        }
        return authors.get(0);
    }

    @Override
    public void delete(long id) {
        jdbc.update("DELETE authors WHERE id = :id", Map.of("id", id));
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query("SELECT id, full_name FROM authors", new AuthorMapper());
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
        Long id = count() + 1;
        return id != null ? id : 0;
    }
}
