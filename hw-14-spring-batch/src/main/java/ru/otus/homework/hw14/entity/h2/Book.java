package ru.otus.homework.hw14.entity.h2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @Column(name = "isbn", nullable = false, updatable = false)
    private String isbn;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToMany
    @JoinTable(name = "book_authors", joinColumns = @JoinColumn(name = "isbn"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "book_genres", joinColumns = @JoinColumn(name = "isbn"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres = new ArrayList<>();
}