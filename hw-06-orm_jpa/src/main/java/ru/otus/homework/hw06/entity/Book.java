package ru.otus.homework.hw06.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
@EqualsAndHashCode
@NamedEntityGraph(name = "bookWithGenres", attributeNodes = @NamedAttributeNode(value = "genres"))
public class Book {
    @Id
    @Column(name = "isbn", nullable = false, updatable = false)
    private String isbn;

    @Column(name = "title", nullable = false)
    private String title;

    public Book(String isbn, String title) {
        this(isbn, title, new HashSet<>(), new HashSet<>());
    }


    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "book_authors", joinColumns = @JoinColumn(name = "isbn"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    @Fetch(FetchMode.SUBSELECT)
    private Set<Author> authors;


    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "book_genres", joinColumns = @JoinColumn(name = "isbn"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres;

}
