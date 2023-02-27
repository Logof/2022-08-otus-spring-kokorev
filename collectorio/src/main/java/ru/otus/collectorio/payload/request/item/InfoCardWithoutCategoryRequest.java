package ru.otus.collectorio.payload.request.item;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class InfoCardWithoutCategoryRequest {
    private Long id;

    private String name;

    private String nameAlt;

    private String releaseType;

    private Date releaseDate;

    private String releaseRegion;

    private String publisher;

    private String developer;

    private String genre;

    private Integer rating;

    private String boxText;

    private String description;
}
