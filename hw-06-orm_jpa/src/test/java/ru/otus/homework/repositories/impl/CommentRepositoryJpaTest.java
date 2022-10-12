package ru.otus.homework.repositories.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.homework.entity.Comment;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Репозиторий на основе Jpa для работы с комментариями к книгам")
@DataJpaTest
@Import(CommentRepositoryJpa.class)
public class CommentRepositoryJpaTest {

    @Autowired
    private CommentRepositoryJpa repositoryJpa;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("Сохранение комментария в БД")
    public void saveTest() {
        Comment actualComment = new Comment(null, "Коммент 1");
        actualComment = repositoryJpa.save(actualComment);
        assertNotEquals(actualComment.getId(), null);
    }

    @Test
    @DisplayName("Поиск комментария в БД по ID")
    public void findByIdTest() {
        Comment actualComment = new Comment(null, "Комментарий");
        actualComment = repositoryJpa.save(actualComment);

        Comment expectedComment = entityManager.find(Comment.class, actualComment.getId());
        assertEquals(expectedComment, actualComment);
    }

    @Test
    @DisplayName("Поиск всех комментариев в БД")
    public void findAllTest() {
        List<Comment> actualComments = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            actualComments.add(repositoryJpa.save(new Comment(null, String.format("Комментарий %d", i))));
        }
        List<Comment> expectedAuthors = repositoryJpa.findAll();
        assertEquals(expectedAuthors, actualComments);
    }

    @Test
    @DisplayName("Обновление текста комментария в БД по ID")
    void updateFullNameByIdTest() {

        Comment actualComment = new Comment(null, "Комментарий");
        actualComment = repositoryJpa.save(actualComment);

        String newAuthorFullName = "А. А. Тестов";

        actualComment.setCommentText(newAuthorFullName);
        repositoryJpa.updateCommentById(actualComment.getId(), newAuthorFullName);

        Comment expectedComment = entityManager.find(Comment.class, actualComment.getId());
        assertEquals(expectedComment, actualComment);
    }

    @Test
    @DisplayName("Удаление комментария в БД по ID")
    void deleteByIdTest() {
        Comment actualComment = new Comment(null, "Комментарий");
        actualComment = repositoryJpa.save(actualComment);
        Comment expectedComment = entityManager.find(Comment.class, actualComment.getId());
        assertEquals(expectedComment, actualComment);

        repositoryJpa.deleteById(actualComment.getId());
        Comment expectedDeletedComment = entityManager.find(Comment.class, actualComment.getId());
        assertNull(expectedDeletedComment);
    }
}
