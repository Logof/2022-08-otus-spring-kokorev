package ru.otus.homework.dao.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.otus.homework.dao.AuthorDao;
import ru.otus.homework.dao.BookAssociationDao;
import ru.otus.homework.dao.GenreDao;
import ru.otus.homework.entity.Author;
import ru.otus.homework.entity.BookAssociation;
import ru.otus.homework.entity.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class BookAssociationDaoJpa implements BookAssociationDao {

    public final static String AUTHOR_CLASS_NAME = Author.class.getSimpleName();

    public final static String GENRE_CLASS_NAME = Genre.class.getSimpleName();

    public final static String COMMENT_CLASS_NAME = Genre.class.getSimpleName();

    @PersistenceContext
    private final EntityManager entityManager;

    private final AuthorDao authorDao;

    private final GenreDao genreDao;


    public BookAssociationDaoJpa(EntityManager entityManager, AuthorDao authorDao, GenreDao genreDao) {
        this.entityManager = entityManager;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @Override
    public boolean isExist(BookAssociation bookAssociation) {
        StringBuilder queryBuilder = new StringBuilder().append("SELECT count(ba) > 0 FROM BookAssociation ba WHERE 1=1");
        Map<String, Object> queryParam = new HashMap<>();
        if (bookAssociation.getIsbn() != null && !bookAssociation.getIsbn().isBlank()) {
            queryBuilder.append("AND ba.isbn = :isbn ");
        }
        if (bookAssociation.getExternalClass() != null && !bookAssociation.getExternalClass().isBlank()) {
            queryBuilder.append("AND ba.external_class = :external_class ");
        }
        queryBuilder.append("AND ba.external_id = :external_id");

        return (boolean) entityManager.createQuery(queryBuilder.toString())
                .setParameter("isbn", bookAssociation.getIsbn())
                .setParameter("external_class", bookAssociation.getExternalClass())
                .setParameter("external_id", bookAssociation.getExternalId()).getSingleResult();

    }

    @Override
    public int delete(BookAssociation bookAssociation) {
        return entityManager.createQuery("DELETE BookAssociation ba WHERE ba.isbn = :isbn " +
                        "AND ba.externalId = :external_id ba,and externalClass = :external_class")
                .setParameter("isbn", bookAssociation.getIsbn())
                .setParameter("external_id", bookAssociation.getExternalId())
                .setParameter("external_class", bookAssociation.getExternalClass())
                .executeUpdate();
    }

    @Override
    public int insert(BookAssociation bookAssociation) {
        return entityManager.createQuery("INSERT INTO assoc (isbn, external_id, external_class) VALUES (:isbn, :external_id, :external_class)")
                .setParameter("isbn", bookAssociation.getIsbn())
                .setParameter("external_id", bookAssociation.getExternalId())
                .setParameter("external_class", bookAssociation.getExternalClass())
                .executeUpdate();
    }

    @Override
    public List<Long> getExternalIdsById(String isbn, String className) {
        String query = "SELECT ba.externalId FROM BookAssociation ba " +
                "where ba.externalClass = :externalClass and ba.isbn = :isbn";
        return entityManager.createQuery(query, Long.class)
                .setParameter("externalClass", className)
                .setParameter("isbn", isbn).getResultList();
    }

    @Override
    public List<Author> getAutors(String isbn) {
        String query = String.format("SELECT a FROM Author a, " +
                "BookAssociation ba " +
                "where ba.externalClass = %s" +
                "  and ba.externalId = a.id" +
                "  and ba.isbn = :isbn", AUTHOR_CLASS_NAME);
        return entityManager.createQuery(query, Author.class).setParameter("isbn", isbn).getResultList();
    }

    @Override
    public List<Genre> getGenres(String isbn) {
        String query = String.format("SELECT a FROM Author a, " +
                "BookAssociation ba " +
                "where ba.externalClass = %s" +
                "  and ba.externalId = a.id" +
                "  and ba.isbn = :isbn", GENRE_CLASS_NAME);
        return entityManager.createQuery(query, Genre.class).setParameter("isbn", isbn).getResultList();
    }

}
