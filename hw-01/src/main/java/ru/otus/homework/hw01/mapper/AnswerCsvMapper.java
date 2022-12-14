package ru.otus.homework.hw01.mapper;

import ru.otus.homework.hw01.entity.Answer;

public class AnswerCsvMapper implements CsvMapper<Answer> {

    private final static int ANSWER_VALUE_INDEX = 0;
    private final static int ANSWER_CORRECT_ATTR_INDEX = 1;

    private final static String TRUE_CONSTANT = "TRUE";

    public Answer toEntity(String[] rawData) {
        return new Answer(rawData[ANSWER_VALUE_INDEX],
                rawData[ANSWER_CORRECT_ATTR_INDEX].trim().equalsIgnoreCase(TRUE_CONSTANT));
    }
}
