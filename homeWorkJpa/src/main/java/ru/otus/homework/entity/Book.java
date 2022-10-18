package ru.otus.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
@NamedEntityGraph(name = "assoc-entity-graph",
        attributeNodes = {
                //@NamedAttributeNode("genres"),
                @NamedAttributeNode("authors"),
                //@NamedAttributeNode("comments"),
        })
public class Book {
    @Id
    @Column(name = "isbn", nullable = false, updatable = false)
    private String isbn;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToMany
    @JoinTable(name = "book_authors", joinColumns = {
            @JoinColumn(name = "id", referencedColumnName = "isbn")
    })
    private List<Author> authors;
}
