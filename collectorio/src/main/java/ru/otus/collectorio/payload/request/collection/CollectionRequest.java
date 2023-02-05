package ru.otus.collectorio.payload.request.collection;

import lombok.Getter;
import lombok.Setter;
import ru.otus.collectorio.entity.Category;
import ru.otus.collectorio.payload.request.collectible.CollectibleItemRequest;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CollectionRequest {

    private Long id;

    private String name;

    private Category category;

    private List<CollectibleItemRequest> collectibleItemList = new ArrayList<>();
}
