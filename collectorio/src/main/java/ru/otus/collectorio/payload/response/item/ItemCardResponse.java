package ru.otus.collectorio.payload.response.item;

import lombok.Getter;
import lombok.Setter;
import ru.otus.collectorio.payload.response.category.CategoryResponse;

import java.util.Date;

@Getter
@Setter
public class ItemCardResponse implements ItemCardDto {
    private Long id;

    private String name;

    private String nameAlt;

    private String releaseType;

    private Date releaseDate;

    private String releaseRegion;

    private String publisher;

    private String developer;

    private CategoryResponse category;

    private String genre;

    private Integer rating;

    private String boxText;

    private String description;
}
