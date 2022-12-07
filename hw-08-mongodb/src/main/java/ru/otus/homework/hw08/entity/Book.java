package ru.otus.homework.hw08.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Book {
    @Id
    private String isbn;

    private String title;

    @DBRef
    private List<Author> authors = new ArrayList<>();

    @DBRef
    private List<Genre> genres = new ArrayList<>();


    public Book(String isbn, String title) {
        this(isbn, title, new ArrayList<>(), new ArrayList<>());
    }
}
