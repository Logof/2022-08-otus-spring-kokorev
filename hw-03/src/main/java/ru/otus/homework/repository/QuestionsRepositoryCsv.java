package ru.otus.homework.repository;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.otus.homework.entity.Question;
import ru.otus.homework.exeption.QuestionNotFoundException;
import ru.otus.homework.mapper.CsvMapper;
import ru.otus.homework.service.FileReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionsRepositoryCsv implements QuestionsRepository {
    private final FileReader questionFileReader;

    private final CsvMapper<Question> questionCsvMapper;

    private final String filePath;

    public QuestionsRepositoryCsv(FileReader questionFileReader, CsvMapper<Question> questionCsvMapper,
                                  @Value("${application.test.file.path}") String filePath,
                                  @Value("${application.test.file.name}") String fileName,
                                  @Value("${application.test.file.localize}") boolean isLocalize,
                                  @Value("${application.locale}") String locale) {
        this.questionFileReader = questionFileReader;
        this.questionCsvMapper = questionCsvMapper;
        this.filePath = (filePath.endsWith(File.separator) ? filePath : filePath + File.separator)
                + (isLocalize ? locale + File.separator : Strings.EMPTY)
                + fileName;
    }

    @Override
    public List<Question> getQuestionList() {
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
