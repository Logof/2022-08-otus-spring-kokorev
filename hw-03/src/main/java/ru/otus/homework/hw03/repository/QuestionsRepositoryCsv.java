package ru.otus.homework.hw03.repository;

import org.springframework.stereotype.Repository;
import ru.otus.homework.hw03.entity.Question;
import ru.otus.homework.hw03.exeption.QuestionNotFoundException;
import ru.otus.homework.hw03.resolver.FilePathResolver;
import ru.otus.homework.hw03.mapper.CsvMapper;
import ru.otus.homework.hw03.service.FileReader;

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
        List<Question> questionList = new ArrayList<>();

        List<String[]> rawDataList;
        rawDataList = questionFileReader.readRawData(filePathResolver.getFilePath());

        for (String[] rawString : rawDataList) {
            if (rawString[0].isEmpty()) {
                throw new QuestionNotFoundException("string number = " + rawDataList.indexOf(rawString) + 1);
            }
            questionList.add(questionCsvMapper.toEntity(rawString));
        }

        return questionList;
    }
}
