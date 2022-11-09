package ru.otus.homework.hw05.helper;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import ru.otus.homework.hw05.entity.Book;
import ru.otus.homework.hw05.entity.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BookExtractor implements ResultSetExtractor<Map<String, Book>> {

    @Override
    public Map<String, Book> extractData(ResultSet rs) throws SQLException, DataAccessException {

        Map<String, Book> books = new HashMap<>();
        while (rs.next()) {
            String isbn = rs.getString("isbn");
            Book book = books.get(isbn);
            if (book == null) {
                book = new Book(isbn, rs.getString("title"), new ArrayList<>(), new ArrayList<>());
                books.put(book.getIsbn(), book);
            }

            if (Objects.nonNull(rs.getString("genre.genreName")) && !rs.getString("genre.genreName").isBlank()) {
                book.getGenres().add(new Genre(rs.getLong("genre.id"), rs.getString("genre.genreName")));
            }
        }
        return books;
    }
}
