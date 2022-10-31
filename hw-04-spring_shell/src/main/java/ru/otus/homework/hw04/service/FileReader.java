package ru.otus.homework.hw04.service;

import ru.otus.homework.hw04.exeption.ErrorReadingFileException;

import java.util.List;

public interface FileReader {

    <T> List<T> readRawData(String filePath) throws ErrorReadingFileException;
}
