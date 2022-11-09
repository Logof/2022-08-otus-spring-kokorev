package ru.otus.homework.hw03.service;

import org.springframework.stereotype.Service;
import ru.otus.homework.hw03.entity.Question;
import ru.otus.homework.hw03.enums.QuestionTypeEnum;

@Service
public class QuestionConverterService implements ConverterService<Question> {

    @Override
    public String toPrintable(Question object) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(object.getQuestionText().trim()).append(":\n");
        if (object.getQuestionType() == QuestionTypeEnum.CHOICE_ANSWERS) {
            for (int i = 0; i < object.getAnswerOptions().size(); i++) {
                stringBuilder.append("\t" + (i + 1) + ". " + object.getAnswerOptions().get(i).getAnswerText().trim()).append("\n");
            }
        }
        return stringBuilder.toString();
    }
}
