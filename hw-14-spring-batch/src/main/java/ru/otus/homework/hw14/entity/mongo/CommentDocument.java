package ru.otus.homework.hw14.entity.mongo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Getter
@Setter
@Document(collection = "comments")
@NoArgsConstructor
@AllArgsConstructor
public class CommentDocument {
    @Id
    private String id;

    private String commentText;

    @DBRef
    BookDocument book;

    public CommentDocument(String commentText, BookDocument book) {
        this(null, commentText, book);
    }
}
