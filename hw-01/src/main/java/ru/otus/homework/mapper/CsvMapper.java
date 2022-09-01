package ru.otus.homework.mapper;

public interface CsvMapper {
    <T> T toEntity(String[] rawData);
}
