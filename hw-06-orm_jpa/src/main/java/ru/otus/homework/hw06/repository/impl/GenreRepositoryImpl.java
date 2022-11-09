package ru.otus.homework.hw06.repository.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.otus.homework.hw06.entity.Book;
import ru.otus.homework.hw06.entity.Genre;
import ru.otus.homework.hw06.repository.GenreRepository;

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
        entityManager.remove(getGenreById(id));
    }

    @Override
    public Set<Genre> getAll() {
        return new HashSet<>(entityManager.createQuery("SELECT g FROM Genre g", Genre.class).getResultList());
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
    public boolean genreHasBooks(long id) {
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
