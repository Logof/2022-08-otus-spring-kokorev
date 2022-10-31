package ru.otus.homework.repository.impl;

import org.springframework.stereotype.Repository;
import ru.otus.homework.entity.Author;
import ru.otus.homework.repository.AuthorRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public AuthorRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Author getAuthorById(long id) {
        return entityManager.find(Author.class, id);
    }

    @Override
    public Author getByFullName(String fullName) {
        try {
            return entityManager.createQuery("SELECT a FROM Author a WHERE a.fullName like :fullName", Author.class)
                    .setParameter("fullName", "%" + fullName + "%").getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


    @Override
    public void delete(long id) {
        entityManager.remove(getAuthorById(id));
    }


    @Override
    public Set<Author> getAll() {
        return new HashSet<>(entityManager.createQuery("SELECT a FROM Author a", Author.class).getResultList());
    }

    @Override
    public boolean authorHasBooks(long id) {
        try {
            return Objects.nonNull(entityManager
                    .createQuery("select true from Book b INNER JOIN b.authors a where a.id = :id", Boolean.class)
                    .setParameter("id", id).getSingleResult());
        } catch (NoResultException e) {
            return false;
        }
    }

    @Override
    public Author save(Author object) {
        if (object == null || object.getFullName() == null || object.getFullName().isBlank()) {
            return null;
        }
        if (object.getId() == null) {
            entityManager.persist(object);
        } else {
            entityManager.merge(object);
        }
        return object;
    }
}
