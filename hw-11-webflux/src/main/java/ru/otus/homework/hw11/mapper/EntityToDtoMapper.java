package ru.otus.homework.hw11.mapper;

import ru.otus.homework.hw11.entity.dto.Dto;

import java.util.List;
import java.util.stream.Collectors;

public interface EntityToDtoMapper<E, T extends Dto> {
    T toDto(E entity);

    default List<T> toDtos(List<E> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
