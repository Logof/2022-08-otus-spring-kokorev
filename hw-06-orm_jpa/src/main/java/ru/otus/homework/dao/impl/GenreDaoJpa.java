package ru.otus.homework.dao.impl;

import org.springframework.stereotype.Repository;
import ru.otus.homework.dao.GenreDao;
import ru.otus.homework.entity.Genre;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class GenreDaoJpa implements GenreDao {
    @PersistenceContext
    private final EntityManager entityManager;

    public GenreDaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public long count() {
        return (long) entityManager.createQuery("SELECT count(g) FROM Genre g").getSingleResult();
    }

    @Override
    public int delete(long id) {
        return entityManager.createQuery("DELETE Genre g WHERE g.id = :id").setParameter("id", id).executeUpdate();
    }

    @Override
    public List<Genre> getAll() {
        return entityManager.createQuery("SELECT g FROM Genre g", Genre.class).getResultList();
    }

    @Override
    public Optional<Genre> getGenreById(Long id) {
        TypedQuery<Genre> query = entityManager.createQuery("SELECT g FROM Genre g WHERE g.id = :id", Genre.class)
                .setParameter("id", id);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public int insert(Genre object) {
        return entityManager.createQuery("INSERT INTO genres (id, genre_name) VALUES (:id, :genre_name)")
                .setParameter("id", object.getId())
                .setParameter("genre_name", object.getGenreName())
                .executeUpdate();
    }

    @Override
    public int update(Genre object) {
        return entityManager.createQuery("UPDATE Genre g SET g.genreName = :genreName WHERE g.id = :id")
                .setParameter("genreName", object.getGenreName())
                .setParameter("id", object.getId())
                .executeUpdate();
    }

    @Override
    public long generateId() {
        return count() + 1;
    }
}
