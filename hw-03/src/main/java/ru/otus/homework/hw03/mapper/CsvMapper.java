package ru.otus.homework.hw03.mapper;

import ru.otus.homework.hw03.entity.BasicEntity;

public interface CsvMapper<T extends BasicEntity> {
    T toEntity(String[] rawData);
}