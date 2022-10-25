package ru.otus.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Document
@Data
public class Author {
    @Id
    private String fullName;

    private List<String> bookIsbns;

    public Author(String fullName) {
        this(fullName, new ArrayList<>());
    }
}
