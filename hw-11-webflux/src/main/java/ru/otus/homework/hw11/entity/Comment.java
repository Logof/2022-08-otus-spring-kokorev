package ru.otus.homework.hw11.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@Document(collection = "comments")
public class Comment {
    @Id
    private UUID id;

    private String commentText;

    @DBRef
    private Book book;

    public Comment(String commentText, Book book) {
        this.id = UUID.randomUUID();
        this.commentText = commentText;
        this.book = book;
    }
}
