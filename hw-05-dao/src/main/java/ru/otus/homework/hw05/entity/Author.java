package ru.otus.homework.hw05.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    private Long id;
    private String fullName;

    public Author(String fullName) {
        this(null, fullName);
    }
}
