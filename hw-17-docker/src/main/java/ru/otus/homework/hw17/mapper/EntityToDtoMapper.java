package ru.otus.homework.hw17.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface EntityToDtoMapper <E, D> {
    D toDto(E entity);

    default List<D> toDtos(List<E> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
