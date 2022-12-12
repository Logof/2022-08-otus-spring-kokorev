package ru.otus.homework.hw08.service;


import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import ru.otus.homework.hw08.entity.Author;
import ru.otus.homework.hw08.service.impl.AuthorServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataMongoTest
@Import(AuthorServiceImpl.class)
public class AuthorServiceTest {

    private static final String COLLECTION_NAME = Author.class.getSimpleName().toLowerCase();

    @Autowired
    private AuthorService authorService;


    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void deleteTest() {
        mongoTemplate.remove(new Query(), COLLECTION_NAME);
        MongoCollection<Document> documentList = mongoTemplate.getCollection(COLLECTION_NAME);
        assertEquals(documentList.countDocuments(), 0);

        mongoTemplate.save(new Author(UUID.randomUUID().toString(), "Author"), COLLECTION_NAME);
        documentList = mongoTemplate.getCollection(COLLECTION_NAME);
        assertEquals(documentList.countDocuments(), 1);

        authorService.delete("Author");
        documentList = mongoTemplate.getCollection(COLLECTION_NAME);
        assertEquals(documentList.countDocuments(), 0);

        mongoTemplate.remove(new Query(), COLLECTION_NAME);
    }

    @Test
    public void addTest() {
        mongoTemplate.remove(new Query(), COLLECTION_NAME);
        Author author = authorService.add("fullName");
        assertEquals(author.getFullName(), "fullName");
        mongoTemplate.remove(new Query(), COLLECTION_NAME);
    }

    @Test
    public void getAllTest() {
        List<Author> actualAuthors = new ArrayList<>();
        Author author1 = authorService.add("fullName 1");
        Author author2 = authorService.add("fullName 2");
        actualAuthors.add(author1);
        actualAuthors.add(author2);

        List<Author> authorList = authorService.getAll();

        assertTrue(authorList.equals(actualAuthors));
        mongoTemplate.remove(new Query(), COLLECTION_NAME);
    }
}
