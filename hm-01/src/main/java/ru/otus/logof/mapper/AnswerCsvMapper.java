package ru.otus.logof.mapper;

import ru.otus.logof.entity.Answer;

public class AnswerCsvMapper implements CsvMapper {

    private final int ANSWER_VALUE_INDEX = 0;
    private final int ANSWER_CORRECT_ATTR_INDEX = 1;

    private final String TRUE_CONSTANT = "TRUE";

    public Answer toEntity(String[] rawData) {
        return new Answer(rawData[ANSWER_VALUE_INDEX],
                rawData[ANSWER_CORRECT_ATTR_INDEX].trim().equalsIgnoreCase(TRUE_CONSTANT));
    }
}
