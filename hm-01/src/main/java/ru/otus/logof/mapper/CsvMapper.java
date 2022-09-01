package ru.otus.logof.mapper;

public interface CsvMapper {

    <T> T toEntity(String[] rawData);
}
