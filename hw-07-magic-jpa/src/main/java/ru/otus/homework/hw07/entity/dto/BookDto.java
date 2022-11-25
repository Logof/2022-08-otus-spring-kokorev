package ru.otus.homework.hw07.entity.dto;

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
public class BookDto implements Dto  {
    private String isbn;
    private String title;
    private List<AuthorDto> authors;
    private List<GenreDto> genres;

    public BookDto(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
        this.genres = new ArrayList<>();
        this.authors = new ArrayList<>();
    }

}
