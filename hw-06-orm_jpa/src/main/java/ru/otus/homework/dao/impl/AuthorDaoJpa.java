package ru.otus.homework.dao.impl;

import org.springframework.stereotype.Repository;
import ru.otus.homework.dao.AuthorDao;
import ru.otus.homework.entity.Author;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorDaoJpa implements AuthorDao {

    @PersistenceContext
    private final EntityManager entityManager;

    public AuthorDaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public long count() {
        return (long) entityManager.createQuery("SELECT count(a) FROM Author a").getSingleResult();
    }

    @Override
    public Optional<Author> getAuthorById(Long id) {
        TypedQuery<Author> query = entityManager.createQuery("SELECT a FROM Author a WHERE a.id = :id", Author.class);
        query.setParameter("id", id);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public int delete(long id) {
        return entityManager.createQuery("DELETE Author WHERE id = :id").executeUpdate();

    }

    @Override
    public List<Author> getAll() {
        return entityManager.createQuery("SELECT a FROM Author a", Author.class).getResultList();
    }


    @Override
    public int insert(Author object) {
        return entityManager.createQuery("INSERT INTO author(id, full_name) VALUES (:id, :full_name)")
                .setParameter("id", object.getId())
                .setParameter("full_name", object.getFullName())
                .executeUpdate();

    }

    @Override
    public int update(Author object) {
        return entityManager.createQuery("UPDATE author a SET a.fullName = :fullName WHERE a.id = :id")
                .setParameter("fullName", object.getFullName())
                .setParameter("id", object.getId())
                .executeUpdate();
    }

    @Override
    public long generateId() {
        return count() + 1;
    }
}
