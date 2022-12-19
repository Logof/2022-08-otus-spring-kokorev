package ru.otus.homework.hw14.entity.mongo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document(collection = "books")
@AllArgsConstructor
@NoArgsConstructor
public class BookDocument {
    @Id
    private String id;

    private String title;

    @DBRef
    private List<GenreDocument> genres;

    @DBRef
    private List<AuthorDocument> authors;

    public BookDocument(String isbn, String title) {
        this.id = isbn;
        this.title = title;
        this.authors = new ArrayList<>();
        this.genres = new ArrayList<>();
    }
}
