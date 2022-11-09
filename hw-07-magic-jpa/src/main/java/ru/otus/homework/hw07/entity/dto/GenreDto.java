package ru.otus.homework.hw07.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenreDto implements Dto {
    private Long id;
    private String genreName;

    public GenreDto(String genreName) {
        this.id = null;
        this.genreName = genreName;
    }
}
