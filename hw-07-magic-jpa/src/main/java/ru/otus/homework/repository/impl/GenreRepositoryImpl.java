package ru.otus.homework.repository.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.otus.homework.entity.Book;
import ru.otus.homework.entity.Genre;
import ru.otus.homework.repository.GenreRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Repository
@Slf4j
public class GenreRepositoryImpl implements GenreRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public GenreRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void deleteById(long id) {
        Genre genre = getGenreById(id);
        entityManager.remove(genre);
    }

    @Override
    public Set<Genre> getAll() {
        return new HashSet<>(entityManager.createQuery("SELECT g FROM Genre g", Genre.class).getResultList());
    }

    @Override
    public Set<Genre> getGenresByIsbn(String isbn) {
        Book book = entityManager.find(Book.class, isbn);
        if (book == null) {
            return null;
        }
        return book.getGenres();
    }

    @Override
    public Genre getGenreById(Long id) {
        return entityManager.find(Genre.class, id);
    }

    @Override
    public Genre getGenreByName(String genreName) {
        return entityManager.createQuery("" +
                        "select g from Genre g " +
                        " where g.genreName = :genreName", Genre.class)
                .setParameter("genreName", genreName).getSingleResult();

    }

    @Override
    public boolean isAttachedToBook(long id) {
        try {
            return Objects.nonNull(entityManager
                    .createQuery("select b from Book b JOIN FETCH b.genres g where g.id = :id", Book.class)
                    .setParameter("id", id).getSingleResult());
        } catch (NoResultException e) {
            return false;
        }
    }

    @Override
    public Genre save(Genre object) {
        if (object == null || object.getGenreName() == null || object.getGenreName().isBlank()) {
            return null;
        }
        if (object.getId() == null) {
            entityManager.persist(object);
        } else {
            entityManager.merge(object);
        }
        entityManager.flush();
        return object;
    }
}
