package ru.otus.homework.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.homework.entity.BookAssociation;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookAssociationMapper implements RowMapper<BookAssociation> {
    @Override
    public BookAssociation mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new BookAssociation(rs.getString("isbn"), rs.getInt("external_id"), rs.getString("external_class"));
    }
}
