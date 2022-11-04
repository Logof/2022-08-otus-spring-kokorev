package ru.otus.homework.hw05.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genre {
    private Long id;
    private String genreName;

    public Genre(String genreName) {
        this(null, genreName);
    }
}
