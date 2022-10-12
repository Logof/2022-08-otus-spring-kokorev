package ru.otus.homework.dao.impl;

import org.springframework.stereotype.Repository;
import ru.otus.homework.dao.BookDao;
import ru.otus.homework.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class BookDaoJpa implements BookDao {

    @PersistenceContext
    private final EntityManager entityManager;
    //private final BookAssociationDao bookAssociationDao;

    public BookDaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;

    }

    @Override
    public long count() {
        return (long) entityManager.createQuery("SELECT count(b) FROM Book b").getSingleResult();
    }

    @Override
    public Optional<Book> getBookById(String isbn) {
        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b WHERE b.isbn = :isbn", Book.class)
                .setParameter("isbn", isbn);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public int delete(String isbn) {
        return entityManager.createQuery("DELETE Book WHERE b.isbn = :isbn").executeUpdate();
    }

    @Override
    public List<Book> getAll() {
        return entityManager.createQuery("SELECT a FROM Book a", Book.class).getResultList();
    }

    @Override
    public int insert(Book book) {
        return entityManager.createQuery("INSERT INTO books(isbn, title) VALUES (:isbn, :title)")
                .setParameter("isbn", book.getIsbn())
                .setParameter("title", book.getTitle())
                .executeUpdate();
    }

    @Override
    public int update(Book book) {
        if (book == null
                || book.getIsbn() == null
                || book.getIsbn().isBlank()
                || book.getTitle() == null
                || book.getTitle().isBlank()) {
            return 0;
        }
        return entityManager.createQuery("UPDATE Book a SET a.title = :title WHERE a.isbn = :isbn")
                .setParameter("isbn", book.getIsbn())
                .setParameter("new_title", book.getTitle())
                .executeUpdate();
    }

}
