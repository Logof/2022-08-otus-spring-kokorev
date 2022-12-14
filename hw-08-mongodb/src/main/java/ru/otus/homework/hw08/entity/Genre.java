package ru.otus.homework.hw08.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@Document
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Genre {
    @Id
    private UUID id;

    private String genreName;

    public Genre(String genreName) {
        this(UUID.randomUUID(), genreName);
    }
}