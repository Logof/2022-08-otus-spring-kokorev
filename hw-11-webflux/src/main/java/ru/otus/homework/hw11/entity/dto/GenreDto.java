package ru.otus.homework.hw11.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenreDto  {
    private UUID id;
    private String genreName;

    public GenreDto(String genreName) {
        this.id = null;
        this.genreName = genreName;
    }
}
