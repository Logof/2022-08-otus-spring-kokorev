package ru.otus.homework.hw14.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import ru.otus.homework.hw14.entity.mongo.BookDocument;
import ru.otus.homework.hw14.entity.mongo.CommentDocument;
import ru.otus.homework.hw14.mapper.EntityToDocumentConverter;
import ru.otus.homework.hw14.repository.MongoBookRepository;
import ru.otus.homework.hw14.repository.MongoCommentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@SpringBatchTest
@AutoConfigureDataMongo
public class ImportJobTest {
    private static final int BOOKS_COUNT = 1;
    private static final int COMMENTS_COUNT = 1;

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private JobRepositoryTestUtils jobRepositoryTestUtils;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MongoBookRepository mongoBookRepository;

    @Autowired
    private MongoCommentRepository mongoCommentRepository;

    @MockBean
    private EntityToDocumentConverter entityToDocumentConverter;

    @BeforeEach
    void clearMetaData() {
        jobRepositoryTestUtils.removeJobExecutions();
        mongoTemplate.remove(new Query(), "books");
        mongoTemplate.remove(new Query(), "genres");
        mongoTemplate.remove(new Query(), "authors");
        mongoTemplate.remove(new Query(), "comments");
    }

    @Test
    void testJob() throws Exception {
        when(entityToDocumentConverter.bookTransformation(any()))
                .thenReturn(new BookDocument(UUID.randomUUID().toString(), "title"));
        when(entityToDocumentConverter.commentTransformation(any()))
                .thenReturn(new CommentDocument(UUID.randomUUID().toString(),
                        new BookDocument(UUID.randomUUID().toString(),
                                "title",
                                new ArrayList<>(),
                                new ArrayList<>())));

        Job job = jobLauncherTestUtils.getJob();
        assertThat(job).isNotNull()
                .extracting(Job::getName)
                .isEqualTo(JobConfig.getJobName());

        JobExecution jobExecution = jobLauncherTestUtils.launchJob();
        assertThat(jobExecution.getExitStatus().getExitCode()).isEqualTo("COMPLETED");

        List<BookDocument> books = mongoBookRepository.findAll();
        assertThat(books).hasSize(BOOKS_COUNT);

        List<CommentDocument> comments = mongoCommentRepository.findAll();
        assertThat(comments).hasSize(COMMENTS_COUNT);
    }
}
