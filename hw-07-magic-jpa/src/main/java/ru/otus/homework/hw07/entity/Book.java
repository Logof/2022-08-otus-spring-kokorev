package ru.otus.homework.hw07.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
@NamedEntityGraph(name = "bookWithAllFields",
        attributeNodes = {
                @NamedAttributeNode("authors")
        })
public class Book {
    @Id
    @Column(name = "isbn", nullable = false, updatable = false)
    private String isbn;

    @Column(name = "title", nullable = false)
    private String title;

    public Book(String isbn, String title) {
        this(isbn, title, new ArrayList<>(), new ArrayList<>());
    }

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, targetEntity = Author.class)
    @JoinTable(name = "book_authors", joinColumns = @JoinColumn(name = "isbn"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;


    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, targetEntity = Genre.class)
    @JoinTable(name = "book_genres", joinColumns = @JoinColumn(name = "isbn"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;

    /*
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Book)) return false;
        Book book = (Book) object;

        return Objects.equals(isbn, book.isbn) & Objects.equals(title, book.title)
                & Objects.equals(genres, book.genres)
                & Objects.equals(authors, book.authors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, isbn, genres, authors);
    }*/
}
