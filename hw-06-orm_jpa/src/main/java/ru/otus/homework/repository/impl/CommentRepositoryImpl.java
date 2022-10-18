package ru.otus.homework.repository.impl;

import org.springframework.stereotype.Repository;
import ru.otus.homework.entity.Comment;
import ru.otus.homework.repository.CommentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CommentRepositoryImpl implements CommentRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public CommentRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Comment save(Comment comment) {
        if (comment == null || comment.getCommentText() == null || comment.getCommentText().isBlank()) {
            return null;
        }
        if (comment.getId() == null) {
            entityManager.persist(comment);
        } else {
            entityManager.merge(comment);
        }
        entityManager.flush();
        return comment;
    }

    @Override
    public void deleteById(long id) {
        Comment comment = entityManager.find(Comment.class, id);
        entityManager.remove(comment);
    }

    @Override
    public List<Comment> getAllByIsbn(String isbn) {
        return entityManager
                .createQuery("select c from Book b join b.comments c where b.isbn = :isbn", Comment.class)
                .setParameter("isbn", isbn).getResultList();
    }
}
