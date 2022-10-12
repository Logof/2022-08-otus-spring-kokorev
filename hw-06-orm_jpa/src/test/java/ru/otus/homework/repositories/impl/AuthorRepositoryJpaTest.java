package ru.otus.homework.repositories.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.homework.entity.Author;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Репозиторий на основе Jpa для работы с авторами")
@DataJpaTest
@Import(AuthorRepositoryJpa.class)
public class AuthorRepositoryJpaTest {
    @Autowired
    private AuthorRepositoryJpa repositoryJpa;

    @Autowired
    private TestEntityManager entityManager;

    @DisplayName("Сохранение автора в БД")
    @Test
    public void saveTest() {
        Author actualAuthor = new Author(null, "Автор Авторович Тестов");
        actualAuthor = repositoryJpa.save(actualAuthor);
        assertNotEquals(actualAuthor.getId(), null);
    }

    @DisplayName("Поиск автора в БД по ID")
    @Test
    public void findByIdTest() {
        Author actualAuthor = new Author(null, "Автор Авторович Тестов");
        actualAuthor = repositoryJpa.save(actualAuthor);
        Author expectedAuthor = entityManager.find(Author.class, actualAuthor.getId());
        assertEquals(expectedAuthor, actualAuthor);
    }

    @DisplayName("Поиск всех авторов в БД")
    @Test
    public void findAllTest() {
        List<Author> actualAuthors = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            actualAuthors.add(repositoryJpa.save(new Author(null, String.format("Автор Авторович Тестов %d", i))));
        }
        List<Author> expectedAuthors = repositoryJpa.findAll();
        assertEquals(expectedAuthors, actualAuthors);
    }


    @DisplayName("Обновление ФИО автора в БД по ID")
    @Test
    void updateFullNameByIdTest() {

        Author actualAuthor = new Author(null, "Автор Авторович Тестов");
        actualAuthor = repositoryJpa.save(actualAuthor);

        String newAuthorFullName = "А. А. Тестов";

        actualAuthor.setFullName(newAuthorFullName);
        repositoryJpa.updateFullNameById(actualAuthor.getId(), newAuthorFullName);
        Author expectedAuthor = entityManager.find(Author.class, actualAuthor.getId());
        assertEquals(expectedAuthor, actualAuthor);
    }

    @DisplayName("Удаление автора из БД по ID")
    @Test
    void deleteByIdTest() {
        Author actualAuthor = new Author(null, "Автор Авторович Тестов");
        actualAuthor = repositoryJpa.save(actualAuthor);
        Author expectedAuthor = entityManager.find(Author.class, actualAuthor.getId());
        assertEquals(expectedAuthor, actualAuthor);

        repositoryJpa.deleteById(actualAuthor.getId());
        Author expectedDeletedAuthor = entityManager.find(Author.class, actualAuthor.getId());
        assertNull(expectedDeletedAuthor);
    }
}
