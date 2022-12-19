package ru.otus.homework.hw14.entity.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "genres")
public class GenreDocument {
    @Id
    private String id;

    private String genreName;

    public GenreDocument(String genreName) {
        this.genreName = genreName;
    }
}
