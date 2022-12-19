package ru.otus.homework.hw14.entity.mongo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class Comment {
    @Id
    private String id;

    private String commentText;

    @DBRef
    private Book book;

    public Comment(String commentText, Book book) {
        this.id = null;
        this.commentText = commentText;
        this.book = book;
    }
}

