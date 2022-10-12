package ru.otus.homework.dao.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.homework.dao.AuthorDao;
import ru.otus.homework.dao.BookAssociationDao;
import ru.otus.homework.dao.GenreDao;
import ru.otus.homework.entity.Author;
import ru.otus.homework.entity.BookAssociation;
import ru.otus.homework.entity.Genre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class BookAssociationDaoImpl implements BookAssociationDao {

    public final static String AUTHOR_CLASS_NAME = Author.class.getSimpleName();

    public final static String GENRE_CLASS_NAME = Genre.class.getSimpleName();
    private final NamedParameterJdbcOperations jdbc;

    private final AuthorDao authorDao;

    private final GenreDao genreDao;


    public BookAssociationDaoImpl(NamedParameterJdbcOperations jdbc, AuthorDao authorDao, GenreDao genreDao) {
        this.jdbc = jdbc;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @Override
    public boolean isExist(BookAssociation bookAssociation) {
        StringBuilder queryBuilder = new StringBuilder().append("SELECT count(1) FROM assoc WHERE 1=1 ");
        Map<String, Object> queryParam = new HashMap<>();
        if (bookAssociation.getIsbn() != null && !bookAssociation.getIsbn().isBlank()) {
            queryBuilder.append("AND isbn = :isbn ");
            queryParam.put("isbn", bookAssociation.getIsbn());
        }
        if (bookAssociation.getExternalClass() != null && !bookAssociation.getExternalClass().isBlank()) {
            queryBuilder.append("AND external_class = :external_class ");
            queryParam.put("external_class", bookAssociation.getExternalClass());
        }
        queryBuilder.append("AND external_id = :external_id");
        queryParam.put("external_id", bookAssociation.getExternalId());
        Long count = jdbc.queryForObject(queryBuilder.toString(), queryParam, Long.class);
        return count != null && count > 0;
    }

    @Override
    public int delete(BookAssociation bookAssociation) {
        return jdbc.update("DELETE FROM assoc WHERE isbn = :isbn AND external_id = :external_id and external_class = :external_class",
                Map.of("isbn", bookAssociation.getIsbn(),
                        "external_id", bookAssociation.getExternalId(),
                        "external_class", bookAssociation.getExternalClass()
                ));
    }

    @Override
    public int insert(BookAssociation bookAssociation) {
        return jdbc.update("INSERT INTO assoc (isbn, external_id, external_class) VALUES (:isbn, :external_id, :external_class)",
                Map.of("isbn", bookAssociation.getIsbn(),
                        "external_id", bookAssociation.getExternalId(),
                        "external_class", bookAssociation.getExternalClass()));
    }

    @Override
    public List<Long> getExternalIdsById(String isbn, String className) {
        return jdbc.queryForList("SELECT external_id FROM assoc WHERE isbn = :isbn AND external_class = :external_class",
                Map.of("isbn", isbn, "external_class", className), Long.class);
    }

    @Override
    public List<Author> getAutors(String isbn) {
        List<Author> authors = new ArrayList<>();
        for (Long id : getExternalIdsById(isbn, AUTHOR_CLASS_NAME)) {
            authors.add(authorDao.getAuthorById(id));
        }
        return authors;
    }

    @Override
    public List<Genre> getGenres(String isbn) {
        List<Genre> genres = new ArrayList<>();
        for (Long id : getExternalIdsById(isbn, GENRE_CLASS_NAME)) {
            genres.add(genreDao.getGenreById(id));
        }
        return genres;
    }

}
