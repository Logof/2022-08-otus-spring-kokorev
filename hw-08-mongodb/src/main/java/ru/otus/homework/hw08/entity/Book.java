package ru.otus.homework.hw08.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
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

    private List<String> authors = new ArrayList<>();

    private List<String> genres = new ArrayList<>();

    private List<Comment> comments = new ArrayList<>();

    public Book(String isbn, String title) {
        this(isbn, title, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }
}
