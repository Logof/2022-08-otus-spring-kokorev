package ru.otus.homework.repository;

import org.springframework.stereotype.Repository;
import ru.otus.homework.entity.Author;
import ru.otus.homework.entity.Book;

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
    public Book getByIsbn(String isbn) {
        return entityManager.find(Book.class, isbn);
    }

    @Override
    public void save(Book book) {
        if (book.getAuthors() != null && book.getAuthors().size() > 0) {
            for (Author author : book.getAuthors()) {
                int position = book.getAuthors().indexOf(author);
                if (author.getId() == null) {
                    author = entityManager.merge(author);
                    book.getAuthors().set(position, author);
                }
            }
        }
        entityManager.merge(book);
    }
}
