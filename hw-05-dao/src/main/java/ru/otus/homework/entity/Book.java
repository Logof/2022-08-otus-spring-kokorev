package ru.otus.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Book {

    private String isbn;

    private String title;

    private List<Author> authors;

    private List<Genre> genres;

    public Book(String isbn, String title) {
        this(isbn, title, new ArrayList<>(), new ArrayList<>());
    }

    public Book() {
        this(null, null, new ArrayList<>(), new ArrayList<>());
    }
}
