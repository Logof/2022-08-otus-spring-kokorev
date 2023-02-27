package ru.otus.collectorio.payload.request.item;

import lombok.Getter;
import lombok.Setter;
import ru.otus.collectorio.payload.request.category.CategoryRequest;

@Getter
@Setter
public class InfoCardRequest {
    private Long id;

    private String name;

    private String nameAlt;

    private String releaseType;

    private CategoryRequest category;

    private String description;
}
