package ru.otus.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.WhereJoinTable;

import javax.persistence.*;
import java.util.List;

@Data
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
    @Id // Позволяет указать какое поле является идентификатором
    private String isbn;

    @Column(name = "title", nullable = false)
    private String title;

    @Fetch(FetchMode.SELECT)
    @ManyToMany(targetEntity = Genre.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "assoc", joinColumns = {
            @JoinColumn(name = "isbn", referencedColumnName = "isbn")},
            inverseJoinColumns = {
                    @JoinColumn(name = "external_id", referencedColumnName = "id")
            })
    @WhereJoinTable(clause = "external_class = 'Genre'")
    private List<Genre> genres;

    @Fetch(FetchMode.SELECT)
    @ManyToMany(targetEntity = Genre.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "assoc", joinColumns = {
            @JoinColumn(name = "isbn", referencedColumnName = "isbn")},
            inverseJoinColumns = {
                    @JoinColumn(name = "external_id", referencedColumnName = "id")
            })
    @WhereJoinTable(clause = "external_class = 'Author'")
    private List<Author> authors;

    @Fetch(FetchMode.SELECT)
    @OneToMany(targetEntity = Genre.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "assoc", joinColumns = {
            @JoinColumn(name = "isbn", referencedColumnName = "isbn")},
            inverseJoinColumns = {
                    @JoinColumn(name = "external_id", referencedColumnName = "id")
            })
    @WhereJoinTable(clause = "external_class = 'Comment'")
    private List<Comment> comments;

}
