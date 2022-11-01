package ru.otus.homework.hw01.mapper;

public interface CsvMapper<T> {
     T toEntity(String[] rawData);
}
