package ru.otus.homework.hw08.service;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import ru.otus.homework.hw08.entity.Genre;
import ru.otus.homework.hw08.service.impl.GenreServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataMongoTest
@Import(GenreServiceImpl.class)
public class GenreServiceTest {

    private static final String COLLECTION_NAME = Genre.class.getSimpleName().toLowerCase();
    @Autowired
    private GenreService genreService;

    @Autowired
    private MongoTemplate mongoTemplate;


    @Test
    public void deleteTest() {
        mongoTemplate.remove(new Query(), COLLECTION_NAME);
        MongoCollection<Document> documentList = mongoTemplate.getCollection(COLLECTION_NAME);
        assertEquals(documentList.countDocuments(), 0);

        mongoTemplate.save(new Genre(UUID.randomUUID(), "genreName"), COLLECTION_NAME);
        documentList = mongoTemplate.getCollection(COLLECTION_NAME);
        assertEquals(documentList.countDocuments(), 1);

        genreService.delete("genreName");
        documentList = mongoTemplate.getCollection(COLLECTION_NAME);
        assertEquals(documentList.countDocuments(), 0);

        mongoTemplate.remove(new Query(), COLLECTION_NAME);
    }

    @Test
    public void addTest() {
        mongoTemplate.remove(new Query(), COLLECTION_NAME);
        Genre genre = genreService.add("genreName");
        assertEquals(genre.getGenreName(), "genreName");
        mongoTemplate.remove(new Query(), COLLECTION_NAME);
    }

    @Test
    public void getAllTest() {
        List<Genre> actualGenres = new ArrayList<>();
        Genre genre1 = genreService.add("genreName 1");
        Genre genre2 = genreService.add("genreName 2");
        actualGenres.add(genre1);
        actualGenres.add(genre2);

        List<Genre> genreList = genreService.getAll();

        assertTrue(genreList.equals(actualGenres));
        mongoTemplate.remove(new Query(), COLLECTION_NAME);
    }
}
