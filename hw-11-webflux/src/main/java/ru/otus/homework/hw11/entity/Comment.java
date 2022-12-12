package ru.otus.homework.hw11.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.UUID;

@Getter
@Setter
@Document(collation = "comments")
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
