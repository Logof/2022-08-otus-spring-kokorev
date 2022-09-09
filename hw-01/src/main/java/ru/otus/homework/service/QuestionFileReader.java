package ru.otus.homework.service;

import java.io.IOException;
import java.util.List;

public interface QuestionFileReader {

    <T> List<T> readRawData(String filePath) throws IOException;
}
