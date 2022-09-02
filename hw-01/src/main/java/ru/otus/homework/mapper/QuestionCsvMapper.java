package ru.otus.homework.mapper;

import ru.otus.homework.entity.Answer;
import ru.otus.homework.entity.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionCsvMapper implements CsvMapper {

    private final static int QUESTION_VALUE_INDEX = 0;
    private final static int ANSWERS_START_INDEX = 1;
    private final static int ANSWERS_COUNT_FIELDS = 2;

    private CsvMapper answerMapper;

    public QuestionCsvMapper(CsvMapper answerMapper) {
        this.answerMapper = answerMapper;
    }

    @Override
    public Question toEntity(String[] rawData) {


        List<Answer> answerList = new ArrayList<>();

        int currentAnswersIndex = ANSWERS_START_INDEX;
        while (currentAnswersIndex < rawData.length && !rawData[currentAnswersIndex].isEmpty()) {
            String[] answerRawData = Arrays.copyOfRange(rawData, currentAnswersIndex, rawData.length);
            answerList.add(answerMapper.toEntity(answerRawData));
            currentAnswersIndex += ANSWERS_COUNT_FIELDS;
        }

        return new Question(rawData[QUESTION_VALUE_INDEX], answerList);
    }
}
