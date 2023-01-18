package ru.otus.collectorio.mapper.collection;

import org.mapstruct.Mapper;
import ru.otus.collectorio.dto.collection.CategoryDto;
import ru.otus.collectorio.entity.collection.Category;
import ru.otus.collectorio.mapper.DtoToEntityMapper;
import ru.otus.collectorio.mapper.EntityToDtoMapper;

@Mapper
public interface CategoryMapper extends EntityToDtoMapper<Category, CategoryDto>,
        DtoToEntityMapper<CategoryDto, Category> {
}
