package ru.otus.homework.entity;

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
@Entity // Указывает, что данный класс является сущностью
@Table(name = "books") // Задает имя таблицы, на которую будет отображаться сущность
@NamedEntityGraph(name = "assoc-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("genres"),
                @NamedAttributeNode("authors"),
                @NamedAttributeNode("comments"),
        })
public class Book {
    @Id
    @Column(name = "isbn", nullable = false, updatable = false)
    private String isbn;

    @Column(name = "title", nullable = false)
    private String title;

    public Book (String isbn, String title) {
        this(isbn, title, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "book_authors",
            joinColumns = @JoinColumn(name = "book_isbn"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;


    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "books_genres",
            joinColumns = @JoinColumn(name = "book_isbn"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;

    //TODO настроить связь
    @OneToMany(targetEntity = Comment.class)
    @JoinColumn(name = "isbn")
    private List<Comment> comments;

}
