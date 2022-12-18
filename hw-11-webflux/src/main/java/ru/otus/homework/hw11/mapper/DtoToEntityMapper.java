package ru.otus.homework.hw11.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface DtoToEntityMapper<T, E> {

    E toEntity(T dto);

    default List<E> toEntities(List<T> dtos) {
        if (dtos == null) {
            return null;
        }

        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
