package ru.otus.homework.repository.impl;

import org.springframework.stereotype.Repository;
import ru.otus.homework.entity.Book;
import ru.otus.homework.exception.DataNotFountException;
import ru.otus.homework.repository.BookRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BookRepositoryImpl implements BookRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public BookRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Book> getAll() {
        return entityManager.createQuery("select b from Book b", Book.class).getResultList();
    }

    @Override
    public Book getBookByIsbn(final String isbn) {
        Book book = entityManager.find(Book.class, isbn);
        if (book == null) {
            throw new DataNotFountException("Not found or too many values found");
        }
        return book;
    }

    @Override
    public void deleteByIsbn(String isbn) {
        entityManager.createQuery("DELETE FROM Book b WHERE b.isbn = :isbn ")
                .setParameter("isbn", isbn)
                .executeUpdate();
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
    public List<Book> getAllByAuthor(String fullName) {
        return entityManager
                .createQuery("select b from Book b join b.authors a where a.fullName like :fullName", Book.class)
                .setParameter("fullName", "%" + fullName + "%")
                .getResultList();
    }

    @Override
    public List<Book> getAllByGenre(String genreName) {
        return entityManager
                .createQuery("select b from Book b join b.genres g where g.genreName = :genreName", Book.class)
                .setParameter("genreName", genreName)
                .getResultList();
    }

}
