package ru.otus.collectorio.mapper.collection;

import org.mapstruct.Mapper;
import ru.otus.collectorio.dto.collection.ItemDto;
import ru.otus.collectorio.entity.collection.Item;
import ru.otus.collectorio.mapper.DtoToEntityMapper;
import ru.otus.collectorio.mapper.EntityToDtoMapper;

@Mapper
public interface ItemMapper extends EntityToDtoMapper<Item, ItemDto>,
        DtoToEntityMapper<ItemDto, Item> {
}
