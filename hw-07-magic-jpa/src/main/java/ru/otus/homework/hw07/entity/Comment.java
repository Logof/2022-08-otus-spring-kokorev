package ru.otus.homework.hw07.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
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
