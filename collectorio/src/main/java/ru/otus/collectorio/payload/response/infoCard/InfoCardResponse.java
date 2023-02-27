package ru.otus.collectorio.payload.response.infoCard;

import lombok.Getter;
import lombok.Setter;
import ru.otus.collectorio.payload.response.category.CategoryResponse;

import java.util.List;

@Getter
@Setter
public class InfoCardResponse {
    private Long id;

    private String name;

    private String nameAlt;

    private CategoryResponse category;

    private String releaseType;

    private List<String> images;

    private String description;
}
