package ru.otus.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private String isbn;

    private String title;

    private List<Author> authors;

    private List<Genre> genres;

    public Book(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
        this.authors = new ArrayList<>();
        this.genres = new ArrayList<>();
    }
}
