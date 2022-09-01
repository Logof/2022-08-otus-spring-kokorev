package mapper;

import entity.Answer;
import entity.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionCsvMapper implements CsvMapper {

    private final int QUESTION_VALUE_INDEX = 0;
    private final int ANSWERS_START_INDEX = 1;
    private final int ANSWERS_COUNT_FIELDS = 2;

    @Override
    public Question toEntity(String[] rawData) {
        CsvMapper answerMapper = new AnswerCsvMapper();

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
