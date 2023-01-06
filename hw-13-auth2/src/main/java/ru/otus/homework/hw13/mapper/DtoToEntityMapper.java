package ru.otus.homework.hw13.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface DtoToEntityMapper <D, E> {

    E toEntity(D dto);

    default List<E> toEntitiesList(List<D> dtos) {
        if (dtos == null) {
            return null;
        }

        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}