package ru.otus.homework.hw06.repository.impl;

import org.springframework.stereotype.Repository;
import ru.otus.homework.hw06.entity.Comment;
import ru.otus.homework.hw06.repository.CommentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Set;

@Repository
public class CommentRepositoryImpl implements CommentRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public CommentRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() == null) {
            entityManager.persist(comment);
        } else {
            entityManager.merge(comment);
        }
        return comment;
    }

    @Override
    public void deleteById(long id) {
        entityManager.remove(entityManager.find(Comment.class, id));
    }

    @Override
    public Set<Comment> getAllByIsbn(String isbn) {
        return new HashSet<>(entityManager
                .createQuery("select c from Comment c JOIN FETCH c.book b where b.isbn = :isbn", Comment.class)
                .setParameter("isbn", isbn).getResultList());
    }
}
