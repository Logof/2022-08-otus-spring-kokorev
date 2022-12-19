package ru.otus.homework.hw14.entity.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "authors")
public class AuthorDocument {
    @Id
    private String id;

    private String fullName;

    public AuthorDocument(String fullName) {
        this.fullName = fullName;
    }
}
