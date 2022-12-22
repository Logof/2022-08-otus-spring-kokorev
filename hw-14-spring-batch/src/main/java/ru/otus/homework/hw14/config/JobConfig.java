package ru.otus.homework.hw14.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.homework.hw14.entity.h2.Book;
import ru.otus.homework.hw14.entity.h2.Comment;
import ru.otus.homework.hw14.entity.mongo.BookDocument;
import ru.otus.homework.hw14.entity.mongo.CommentDocument;
import ru.otus.homework.hw14.mapper.EntityToDocumentConverter;
import ru.otus.homework.hw14.repository.BookRepository;
import ru.otus.homework.hw14.repository.CommentRepository;
import ru.otus.homework.hw14.repository.MongoBookRepository;
import ru.otus.homework.hw14.repository.MongoCommentRepository;

import java.util.HashMap;

@Slf4j
@Configuration
public class JobConfig {
    private static final int CHUNK_SIZE = 5;

    public static final String IMPORT_H2_TO_MONGO_JOB_NAME = "importH2ToMongoJob";

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @StepScope
    @Bean
    public RepositoryItemReader<Book> bookReader(BookRepository h2BooksRepository) {
        RepositoryItemReader<Book> reader = new RepositoryItemReader<>();
        reader.setRepository(h2BooksRepository);
        reader.setMethodName("findAll");
        reader.setSort(new HashMap<>());
        return reader;
    }

    @StepScope
    @Bean
    public ItemProcessor<Book, BookDocument> bookProcessor(EntityToDocumentConverter convertor) {
        return (ItemProcessor<Book, BookDocument>) convertor::bookTransformation;
    }

    @StepScope
    @Bean
    public RepositoryItemWriter<BookDocument> bookWriter(MongoBookRepository bookRepository) {
        RepositoryItemWriter writer = new RepositoryItemWriter<>();
        writer.setRepository(bookRepository);
        writer.setMethodName("save");
        return writer;
    }

    @Bean
    public Step step1MigateBooks(RepositoryItemReader<Book> bookReader,
                                 /*@Qualifier("bookProcessor") */ItemProcessor<Book, BookDocument> bookProcessor,
                                 RepositoryItemWriter<BookDocument> bookWriter) {
        return stepBuilderFactory.get("step1_MigateBooks")
                .<Book, BookDocument>chunk(CHUNK_SIZE)
                .reader(bookReader)
                .processor(bookProcessor)
                .writer(bookWriter)
                .build();
    }

    @StepScope
    @Bean
    public RepositoryItemReader<Comment> commentReader(CommentRepository commentRepository) {
        RepositoryItemReader<Comment> reader = new RepositoryItemReader<>();
        reader.setRepository(commentRepository);
        reader.setMethodName("findAll");
        reader.setSort(new HashMap<>());
        return reader;
    }

    @StepScope
    @Bean
    public ItemProcessor<Comment, CommentDocument> commentProcessor(EntityToDocumentConverter convertor) {
        return (ItemProcessor<Comment, CommentDocument>) convertor::commentTransformation;
    }

    @StepScope
    @Bean
    public RepositoryItemWriter<CommentDocument> commentWriter(MongoCommentRepository commentsRepository) {
        RepositoryItemWriter writer = new RepositoryItemWriter();
        writer.setRepository(commentsRepository);
        writer.setMethodName("save");
        return writer;
    }

    @Bean
    public Step step2MigateComments(RepositoryItemReader<Comment> commentReader,
                                    ItemProcessor<Comment, CommentDocument> commentProcessor,
                                    RepositoryItemWriter<CommentDocument> commentWriter
    ) {
        return stepBuilderFactory.get("step2_MigateComments")
                .<Comment, CommentDocument>chunk(CHUNK_SIZE)
                .reader(commentReader)
                .processor(commentProcessor)
                .writer(commentWriter)
                .build();
    }

    @Bean
    public Job importH2ToMongoJob(Step step1MigateBooks, Step step2MigateComments) {

        return jobBuilderFactory.get(IMPORT_H2_TO_MONGO_JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .start(step1MigateBooks)
                .next(step2MigateComments)
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(JobExecution jobExecution) {
                        log.info("Начало job");
                    }

                    @Override
                    public void afterJob(JobExecution jobExecution) {
                        log.info("Конец job");
                    }
                })
                .build();
    }
}
