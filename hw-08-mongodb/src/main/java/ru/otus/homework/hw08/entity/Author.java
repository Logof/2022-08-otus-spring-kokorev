package ru.otus.homework.hw08.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Author implements Serializable {
    @Id
    private String id;

    private String fullName;

    private List<Book> bookList = new ArrayList<>();

    public Author(String fullName) {
        this(null, fullName, new ArrayList<>());
    }
}
