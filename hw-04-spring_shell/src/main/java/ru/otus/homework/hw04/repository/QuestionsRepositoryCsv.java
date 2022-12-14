package ru.otus.homework.hw04.repository;

import org.springframework.stereotype.Repository;
import ru.otus.homework.hw04.entity.Question;
import ru.otus.homework.hw04.exeption.QuestionNotFoundException;
import ru.otus.homework.hw04.mapper.CsvMapper;
import ru.otus.homework.hw04.resolver.FilePathResolver;
import ru.otus.homework.hw04.service.FileReader;

import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionsRepositoryCsv implements QuestionsRepository {
    private final FileReader questionFileReader;

    private final CsvMapper<Question> questionCsvMapper;

    private final FilePathResolver filePathResolver;

    public QuestionsRepositoryCsv(FileReader questionFileReader, CsvMapper<Question> questionCsvMapper,
                                  FilePathResolver filePathResolver) {
        this.questionFileReader = questionFileReader;
        this.questionCsvMapper = questionCsvMapper;
        this.filePathResolver = filePathResolver;
    }

    @Override
    public List<Question> getQuestionList() {
        return getQuestionList(filePathResolver.getFilePath());
    }

    @Override
    public List<Question> getQuestionList(String filePath) {
        List<Question> questionList = new ArrayList<>();

        List<String[]> rawDataList;
        rawDataList = questionFileReader.readRawData(filePath);

        for (String[] rawString : rawDataList) {
            if (rawString[0].isEmpty()) {
                throw new QuestionNotFoundException("string number = " + rawDataList.indexOf(rawString) + 1);
            }
            questionList.add(questionCsvMapper.toEntity(rawString));
        }

        return questionList;
    }
}
