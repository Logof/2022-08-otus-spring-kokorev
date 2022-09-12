package ru.otus.homework.mapper;

import ru.otus.homework.entity.TestEntity;

public interface CsvMapper<T extends TestEntity> {
    T toEntity(String[] rawData);
}