package ru.otus.collectorio.dto.collection;

import lombok.Getter;
import lombok.Setter;
import ru.otus.collectorio.entity.collection.Item;

import java.util.List;

@Getter
@Setter
public class CollectionDto {

    private Long id;

    private List<Item> item;
}
