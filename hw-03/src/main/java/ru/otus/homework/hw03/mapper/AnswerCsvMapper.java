package ru.otus.homework.hw03.mapper;

import org.springframework.stereotype.Component;
import ru.otus.homework.hw03.entity.Answer;

@Component
public class AnswerCsvMapper implements CsvMapper<Answer> {
    private final static int ANSWER_VALUE_INDEX = 0;
    private final static int ANSWER_CORRECT_ATTR_INDEX = 1;
    private final static String TRUE_CONSTANT = "TRUE";

    public Answer toEntity(String[] rawData) {
        if (rawData == null || rawData.length == 0) {
            return null;
        }

        if (rawData.length < 2) {
            return new Answer(rawData[ANSWER_VALUE_INDEX], false);
        }

        return new Answer(rawData[ANSWER_VALUE_INDEX],
                rawData[ANSWER_CORRECT_ATTR_INDEX].trim().equalsIgnoreCase(TRUE_CONSTANT));
    }

}
