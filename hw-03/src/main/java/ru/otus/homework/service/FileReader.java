package ru.otus.homework.service;

import ru.otus.homework.exeption.ErrorReadingFileException;

import java.util.List;

public interface FileReader {

    <T> List<T> readRawData(String filePath) throws ErrorReadingFileException;
}
