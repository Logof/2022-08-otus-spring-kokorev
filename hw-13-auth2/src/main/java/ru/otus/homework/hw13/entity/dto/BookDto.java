package ru.otus.homework.hw13.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto implements Dto {
    private long isbn;
    private String title;
    private List<AuthorDto> authors;
    private List<GenreDto> genres;

    public BookDto(long isbn, String title) {
        this.isbn = isbn;
        this.title = title;
        this.genres = new ArrayList<>();
        this.authors = new ArrayList<>();
    }

}
