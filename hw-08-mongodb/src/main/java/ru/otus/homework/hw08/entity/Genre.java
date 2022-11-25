package ru.otus.homework.hw08.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document
@AllArgsConstructor
@NoArgsConstructor

@EqualsAndHashCode
public class Genre {
    @Id
    private String genreName;

    private List<String> isbnList = new ArrayList<>();
}