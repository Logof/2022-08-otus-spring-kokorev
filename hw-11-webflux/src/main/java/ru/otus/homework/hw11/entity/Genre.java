package ru.otus.homework.hw11.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@Document(collection = "genres")
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