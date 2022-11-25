package ru.otus.homework.hw06.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment_text", nullable = false, unique = true)
    private String commentText;

    public Comment(String commentText, Book book) {
        this(null, commentText, book);
    }

    @ManyToOne
    @JoinColumn(name = "isbn")
    private Book book;
}
