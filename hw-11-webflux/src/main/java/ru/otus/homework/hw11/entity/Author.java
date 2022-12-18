package ru.otus.homework.hw11.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@Document(collection = "authors")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Author {
    @Id
    private UUID id;

    private String fullName;

    public Author(String fullName) {
        this(UUID.randomUUID(), fullName);
    }
}
