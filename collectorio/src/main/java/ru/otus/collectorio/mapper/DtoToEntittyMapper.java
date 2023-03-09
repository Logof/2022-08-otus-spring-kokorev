package ru.otus.collectorio.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface DtoToEntittyMapper<D, E> {

    E toEntity(D dto);

    default List<E> toEntities(List<D> dtos) {
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
