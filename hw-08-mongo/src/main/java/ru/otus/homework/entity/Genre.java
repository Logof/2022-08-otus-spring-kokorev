package ru.otus.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Genre {
    @Id
    private String genreName;

    private List<String> bookIsbns;

    public Genre(String genreName) {
        this(genreName, new ArrayList<>());
    }
}
