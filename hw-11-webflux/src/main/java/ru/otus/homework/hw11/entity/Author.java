package ru.otus.homework.hw11.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "authors")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Author {
    @Id
    private String id;

    private String fullName;

    public Author(String fullName) {
        this(null, fullName);
    }
}
