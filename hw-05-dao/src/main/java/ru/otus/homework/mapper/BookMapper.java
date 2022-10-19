package ru.otus.homework.mapper;

import org.springframework.jdbc.core.ResultSetExtractor;
import ru.otus.homework.entity.Author;
import ru.otus.homework.entity.Book;
import ru.otus.homework.entity.Genre;

import java.util.ArrayList;
import java.util.List;

public class BookMapper {

    public static final ResultSetExtractor<Book> bookWithAuthorsAndGenresResultExtractor = (resultSet) -> {
        Book book = null;
        while (resultSet.next()) {
            if (resultSet.isFirst()) {
                book = new Book(resultSet.getString("isbn"), resultSet.getString("title"));
            }
            String authorFullName = resultSet.getString("authors.fullName");
            if (authorFullName != null && !authorFullName.isBlank()) {
                book.getAuthors().add(new Author(resultSet.getLong("authors.id"), resultSet.getString("authors.fullName")));
            }

            String genreName = resultSet.getString("genres.genreName");
            if (genreName != null && !genreName.isBlank()) {
                book.getGenres().add(new Genre(resultSet.getLong("genres.id"), resultSet.getString("genres.genreName")));
            }
        }
        return book;
    };


    public static final ResultSetExtractor<List<Book>> booksWithAuthorsAndGenresResultExtractor = (resultSet) -> {

        Book currBook = null;
        List<Book> bookList = new ArrayList<>();
        while (resultSet.next()) {
            if (resultSet.isFirst()) {
                currBook = new Book(resultSet.getString("isbn"), resultSet.getString("title"));
                bookList.add(currBook);
            } else {
                if (!resultSet.getString("isbn").equalsIgnoreCase(currBook.getIsbn())) {
                    currBook = new Book(resultSet.getString("isbn"), resultSet.getString("title"));
                    bookList.add(currBook);
                }
            }

            String authorFullName = resultSet.getString("authors.fullName");
            if (authorFullName != null && !authorFullName.isBlank()) {
                currBook.getAuthors().add(new Author(resultSet.getLong("authors.id"), resultSet.getString("authors.fullName")));
            }

            String genreName = resultSet.getString("genres.genreName");
            if (genreName != null && !genreName.isBlank()) {
                currBook.getGenres().add(new Genre(resultSet.getLong("genres.id"), resultSet.getString("genres.genreName")));
            }
        }
        return bookList;
    };
}
