package ru.otus.collectorio.dto.collection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {
    private Long id;

    private String name;

    private String nameAlt;

    private CategoryDto category;

    private GenreDto genre;

    private String releaseType;

    private String description;
}
