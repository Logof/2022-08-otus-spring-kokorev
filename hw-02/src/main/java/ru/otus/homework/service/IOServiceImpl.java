package ru.otus.homework.service;

import ru.otus.homework.entity.Question;
import ru.otus.homework.enums.QuestionTypeEnum;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class IOServiceImpl implements IOService {

    private final PrintStream output;

    private final Scanner input;

    public IOServiceImpl(OutputStream outputStream, InputStream inputStream) {
        this.output = new PrintStream(outputStream);
        this.input = new Scanner(inputStream);
    }

    @Override
    public void messageOutput(String message) {
        output.println(message);
    }

    @Override
    public String readStringWithPrompt(String prompt) {
        output.println(prompt);
        return input.nextLine();
    }

    @Override
    public void printQuestion(Question question) {
        messageOutput(question.getQuestionText());
        if (question.getQuestionType() == QuestionTypeEnum.CHOICE_ANSWERS) {
            for (int i = 0; i < question.getAnswerOptions().size(); i++) {
                messageOutput("\t" + (i+1) + ". "+ question.getAnswerOptions().get(i).getAnswerText());
            }
        }
    }
}
