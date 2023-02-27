package ru.otus.collectorio.payload.request.collection;

import lombok.Getter;
import lombok.Setter;
import ru.otus.collectorio.entity.Category;

@Getter
@Setter
public class CollectionRequest {

    private Long id;

    private String name;

    private Category category;
}
