package ru.otus.homework.hw02.mapper;

import ru.otus.homework.hw02.entity.BasicEntity;

public interface CsvMapper<T extends BasicEntity> {
    T toEntity(String[] rawData);
}