package com.github.logof.collectorio.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface EntityToDtoMapper<E, T> {
    T toDto(E entity);

    default List<T> toDtos(List<E> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
