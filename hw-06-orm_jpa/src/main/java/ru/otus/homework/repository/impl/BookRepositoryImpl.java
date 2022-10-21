package ru.otus.homework.repository.impl;

import org.springframework.stereotype.Repository;
import ru.otus.homework.entity.Book;
import ru.otus.homework.exception.DataNotFountException;
import ru.otus.homework.repository.BookRepository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Repository
public class BookRepositoryImpl implements BookRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public BookRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Set<Book> getAll() {
        EntityGraph entityGraph = entityManager.getEntityGraph("bookWithAll");
        return new HashSet<>(entityManager.createQuery("select b " +
                "from Book b", Book.class).setHint("javax.persistence.fetchgraph", entityGraph).getResultList());
    }

    @Override
    public Book getBookByIsbn(final String isbn) {
        Map<String, Object> hints = new HashMap();
        hints.put("javax.persistence.fetchgraph", entityManager.getEntityGraph("bookWithAll"));

        Book book = entityManager.find(Book.class, isbn, hints);
        if (book == null) {
            throw new DataNotFountException("Not found or too many values found");
        }
        return book;
    }

    @Override
    public void deleteByIsbn(String isbn) {
        entityManager.remove(getBookByIsbn(isbn));
    }


    @Override
    public Book update(Book book) {
        return entityManager.merge(book);
    }

    @Override
    public void insert(Book book) {
        entityManager.persist(book);
    }

    @Override
    public Set<Book> getAllByAuthor(String fullName) {
        return new HashSet<>(entityManager
                .createQuery("select b from Book b JOIN FETCH b.authors a where a.fullName like :fullName", Book.class)
                .setParameter("fullName", "%" + fullName + "%")
                .getResultList());
    }

    @Override
    public Set<Book> getAllByGenre(String genreName) {
        return new HashSet<>(entityManager
                .createQuery("select b from Book b JOIN FETCH b.genres g where g.genreName = :genreName", Book.class)
                .setParameter("genreName", genreName)
                .getResultList());
    }

}
