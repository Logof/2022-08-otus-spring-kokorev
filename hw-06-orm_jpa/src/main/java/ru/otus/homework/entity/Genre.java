package ru.otus.homework.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "GENRE_NAME", nullable = false, unique = true)
    private String genreName;

    public Genre() {
        this(null, null);
    }
    public Genre(String genreName) {
        this(null, genreName);
    }
    public Genre(Long id, String genreName) {
        this.id = id;
        this.genreName = genreName;
    }


}
