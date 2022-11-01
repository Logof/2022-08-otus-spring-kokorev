package ru.otus.homework.hw02.service;

import ru.otus.homework.hw02.exeption.ErrorReadingFileException;

import java.util.List;

public interface FileReader {

    <T> List<T> readRawData(String filePath) throws ErrorReadingFileException;
}
