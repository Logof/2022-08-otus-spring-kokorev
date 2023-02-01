package ru.otus.collectorio.payload.request.item;

import lombok.Getter;
import lombok.Setter;
import ru.otus.collectorio.entity.Category;

@Getter
@Setter
public class ItemFindRequest {

    private String name;
    private Category category;
}
