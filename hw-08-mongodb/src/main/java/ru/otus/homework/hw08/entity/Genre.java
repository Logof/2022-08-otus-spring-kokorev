package ru.otus.homework.hw08.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Genre {
    @Id
    private String id;

    private String genreName;

    public Genre(String genreName) {
        this(null, genreName);
    }
}