package ru.otus.homework.hw13.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private long isbn;
    private String title;
    private List<AuthorDto> authors;
    private List<GenreDto> genres;
}
