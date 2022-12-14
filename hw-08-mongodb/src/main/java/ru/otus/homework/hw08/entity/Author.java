package ru.otus.homework.hw08.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Document
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Author implements Serializable {
    @Id
    private UUID id;

    private String fullName;

    public Author(String fullName) {
        this(UUID.randomUUID(), fullName);
    }
}
