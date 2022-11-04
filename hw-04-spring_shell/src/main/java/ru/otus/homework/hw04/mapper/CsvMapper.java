package ru.otus.homework.hw04.mapper;

import ru.otus.homework.hw04.entity.BasicEntity;

public interface CsvMapper<T extends BasicEntity> {
    T toEntity(String[] rawData);
}