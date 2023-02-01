package ru.otus.collectorio.payload.response.item;

import lombok.Getter;
import lombok.Setter;
import ru.otus.collectorio.entity.Category;

@Getter
@Setter
public class ItemListResponse {
    private Long id;

    private String name;

    private String nameAlt;

    private Category category;
}
