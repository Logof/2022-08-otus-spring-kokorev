package ru.otus.homework.hw06.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "authors")
@EqualsAndHashCode
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false, unique = true)
    private String fullName;

    public Author() {
        this(null, null);
    }

    public Author(String fullName) {
        this(null, fullName);
    }

    public Author(Long id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

}
