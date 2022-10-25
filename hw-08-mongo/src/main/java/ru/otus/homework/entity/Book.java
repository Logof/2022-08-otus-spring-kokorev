package ru.otus.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Book {
    @Id
    private String isbn;

    private String title;

    public Book(String isbn, String title) {
        this(isbn, title, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    private List<String> authors;

    private List<String> genres;

    private List<String> commentIds;

}
