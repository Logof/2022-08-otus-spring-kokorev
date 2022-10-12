package ru.otus.homework.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.homework.entity.Author;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
        Author author = new Author();
        author.setId(rs.getLong("id"));
        author.setFullName(rs.getString("full_name"));
        return author;
    }
}
