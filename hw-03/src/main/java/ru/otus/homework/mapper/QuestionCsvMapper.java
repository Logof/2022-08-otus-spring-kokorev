package ru.otus.homework.mapper;

import org.springframework.stereotype.Component;
import ru.otus.homework.entity.Answer;
import ru.otus.homework.entity.Question;
import ru.otus.homework.enums.QuestionTypeEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Component
public class QuestionCsvMapper implements CsvMapper<Question> {

    private final static int QUESTION_VALUE_INDEX = 0;
    private final static int QUESTION_TYPE = 1;
    private final static int ANSWERS_START_INDEX = 2;
    private final static int ANSWERS_COUNT_FIELDS = 2;

    private final CsvMapper<Answer> answerCsvMapper;

    public QuestionCsvMapper(CsvMapper<Answer> answerCsvMapper) {
        this.answerCsvMapper = answerCsvMapper;
    }

    @Override
    public Question toEntity(String[] rawData) {
        List<Answer> answerList = new ArrayList<>();

        QuestionTypeEnum questionTypeEnum = QuestionTypeEnum.valueOf(rawData[QUESTION_TYPE].toUpperCase(Locale.ROOT));

        int currentAnswersIndex = ANSWERS_START_INDEX;

        switch (questionTypeEnum) {
            case CHOICE_ANSWERS:
                while (currentAnswersIndex < rawData.length && !rawData[currentAnswersIndex].isEmpty()) {
                    String[] answerRawData = Arrays.copyOfRange(rawData, currentAnswersIndex, rawData.length);
                    answerList.add(answerCsvMapper.toEntity(answerRawData));
                    currentAnswersIndex += ANSWERS_COUNT_FIELDS;
                }
                break;
            case ENTER_ANSWER:
                String[] answerRawData = Arrays.copyOfRange(rawData, ANSWERS_START_INDEX,
                        ANSWERS_START_INDEX + ANSWERS_COUNT_FIELDS);
                answerList.add(answerCsvMapper.toEntity(answerRawData));
                break;
            default:
                break;
        }

        return new Question(rawData[QUESTION_VALUE_INDEX], questionTypeEnum, answerList);
    }
}
