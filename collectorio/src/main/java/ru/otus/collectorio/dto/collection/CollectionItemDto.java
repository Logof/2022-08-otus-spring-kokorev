package ru.otus.collectorio.dto.collection;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CollectionItemDto {
    private Long id;

    private String name;

    List<ItemDto> items;
}
