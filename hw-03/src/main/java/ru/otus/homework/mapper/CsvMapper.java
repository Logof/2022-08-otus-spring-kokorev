package ru.otus.homework.mapper;

import ru.otus.homework.entity.BasicEntity;

public interface CsvMapper<T extends BasicEntity> {
    T toEntity(String[] rawData);
}