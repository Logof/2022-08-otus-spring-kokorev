package ru.otus.collectorio.payload.response.item;

import lombok.Getter;
import lombok.Setter;
import ru.otus.collectorio.entity.Category;

import java.util.List;

@Getter
@Setter
public class ItemCardListResponse implements ItemCardDto {
    private Long id;

    private String name;

    private String nameAlt;

    private Category category;

    private List<String> images;
}
