package ru.otus.homework.hw07.mapper;

import ru.otus.homework.hw07.entity.dto.Dto;

import java.util.List;
import java.util.stream.Collectors;

public interface DtoToEntityMapper<T extends Dto, E> {

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
