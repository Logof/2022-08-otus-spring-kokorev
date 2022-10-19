package ru.otus.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
@NamedEntityGraph(name = "bookWithAll",
        attributeNodes = {
                @NamedAttributeNode(value = "genres", subgraph = "genresGraph"),
                @NamedAttributeNode(value = "authors", subgraph = "authorsGraph"),
                @NamedAttributeNode(value = "comments", subgraph = "commentsGraph"),
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "genresGraph",
                        attributeNodes = {
                                @NamedAttributeNode(value = "id"),
                                @NamedAttributeNode(value = "genreName")
                        }
                ),
                @NamedSubgraph(
                        name = "authorsGraph",
                        attributeNodes = {
                                @NamedAttributeNode(value = "id"),
                                @NamedAttributeNode(value = "fullName")
                        }
                ),
                @NamedSubgraph(
                        name = "commentsGraph",
                        attributeNodes = {
                                @NamedAttributeNode(value = "id"),
                                @NamedAttributeNode(value = "commentText")
                        }
                )
        })
public class Book {
    @Id
    @Column(name = "isbn", nullable = false, updatable = false)
    private String isbn;

    @Column(name = "title", nullable = false)
    private String title;

    public Book(String isbn, String title) {
        this(isbn, title, new HashSet<>(), new HashSet<>(), new HashSet<>());
    }


    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "book_authors", joinColumns = @JoinColumn(name = "isbn"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors;


    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "book_genres", joinColumns = @JoinColumn(name = "isbn"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "isbn", referencedColumnName = "isbn")

    //@JoinTable(name = "COMMENTS", inverseJoinColumns = @JoinColumn(name = "ISBN")
    //        inverseJoinColumns = @JoinColumn(name = "isbn")
    //)
    private Set<Comment> comments;

}
