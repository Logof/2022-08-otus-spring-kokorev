package ru.otus.homework.repositories.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.entity.Book;
import ru.otus.homework.repositories.BookRepository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryJpa implements BookRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public BookRepositoryJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Book save(Book book) {
        entityManager.persist(book);
        return book;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> findByIsbn(String isbn) {
        return Optional.ofNullable(entityManager.find(Book.class, isbn));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll() {
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("assoc-entity-graph");
        TypedQuery<Book> query = entityManager.createQuery("select b from Book b join fetch b.isbn", Book.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void updateTitleById(String isbn, String title) {
        entityManager.createQuery("update Book s set s.title = :title where s.isbn = :isbn")
                .setParameter("title", title)
                .setParameter("isbn", isbn)
                .executeUpdate();
    }

    @Override
    @Transactional
    public void deleteByIsbn(String isbn) {
        entityManager.createQuery("delete from Book s where s.isbn = :isbn")
                .setParameter("isbn", isbn)
                .executeUpdate();
    }

}
