package ru.otus.homework.repositories.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.entity.Genre;
import ru.otus.homework.repositories.GenreRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class GenreRepositoryJpa implements GenreRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    public GenreRepositoryJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Genre save(Genre genre) {
        if (genre.getId() == null) {
            entityManager.persist(genre);
            return genre;
        } else {
            return entityManager.merge(genre);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Genre> findById(long id) {
        return Optional.ofNullable(entityManager.find(Genre.class, id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genre> findAll() {
        return entityManager.createQuery("select g from Genre g", Genre.class).getResultList();
    }

    @Override
    @Transactional
    public void updateNameById(long id, String name) {
        entityManager.createQuery("update Genre g set g.name = :name where g.id = :id")
                .setParameter("id", id)
                .setParameter("name", name)
                .executeUpdate();
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        entityManager.createQuery("delete from Genre g where g.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
