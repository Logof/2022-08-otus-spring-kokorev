package ru.otus.homework.hw11.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Getter
@Setter
@Document(collation = "authors")
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
