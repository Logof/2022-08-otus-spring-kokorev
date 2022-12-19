package ru.otus.homework.hw14.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.MethodInvokingTaskletAdapter;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.data.builder.MongoItemReaderBuilder;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import ru.otus.homework.hw14.entity.mongo.Book;
import ru.otus.homework.hw14.repository.mongo.AuthorRepository;
import ru.otus.homework.hw14.repository.mongo.BookRepository;
import ru.otus.homework.hw14.repository.mongo.CommentRepository;
import ru.otus.homework.hw14.repository.mongo.GenreRepository;

import java.util.Collections;

@Component
public class DataTransferJob {
    public static final String JOB_NAME = "DataTransferJob";
    private static final int CHUNK_SIZE = 5;
    public final JobBuilderFactory jobBuilderFactory;
    public final MongoTemplate mongoTemplate;
    public final StepBuilderFactory stepBuilderFactory;
    public final ru.otus.homework.hw14.repository.mongo.AuthorRepository authorRepositoryTarget;
    public final ru.otus.homework.hw14.repository.mongo.GenreRepository genreRepositoryTarget;
    public final ru.otus.homework.hw14.repository.mongo.BookRepository bookRepositoryTarget;
    public final ru.otus.homework.hw14.repository.mongo.CommentRepository commentRepositoryTarget;
    public final ru.otus.homework.hw14.repository.h2.AuthorRepository authorRepositorySource;
    public final ru.otus.homework.hw14.repository.h2.GenreRepository genreRepositorySource;
    public final ru.otus.homework.hw14.repository.h2.BookRepository bookRepositorySource;
    public final ru.otus.homework.hw14.repository.h2.CommentRepository bookCommentRepositorySource;


    public DataTransferJob(JobBuilderFactory jobBuilderFactory,
                           MongoTemplate mongoTemplate,
                           StepBuilderFactory stepBuilderFactory,
                           AuthorRepository authorRepositoryTarget,
                           GenreRepository genreRepositoryTarget,
                           BookRepository bookRepositoryTarget,
                           CommentRepository commentRepositoryTarget,
                           ru.otus.homework.hw14.repository.h2.AuthorRepository authorRepositorySource,
                           ru.otus.homework.hw14.repository.h2.GenreRepository genreRepositorySource,
                           ru.otus.homework.hw14.repository.h2.BookRepository bookRepositorySource,
                           ru.otus.homework.hw14.repository.h2.CommentRepository bookCommentRepositorySource) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.mongoTemplate = mongoTemplate;
        this.stepBuilderFactory = stepBuilderFactory;
        this.authorRepositoryTarget = authorRepositoryTarget;
        this.genreRepositoryTarget = genreRepositoryTarget;
        this.bookRepositoryTarget = bookRepositoryTarget;
        this.commentRepositoryTarget = commentRepositoryTarget;
        this.authorRepositorySource = authorRepositorySource;
        this.genreRepositorySource = genreRepositorySource;
        this.bookRepositorySource = bookRepositorySource;
        this.bookCommentRepositorySource = bookCommentRepositorySource;
    }

    public Job getAuthorJob() {
        return jobBuilderFactory.get(JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .flow(transformAuthorStep())
                .next(transformGenreStep())
                .next(transformBookStep())
                //TODO добавить комменты
                .next(cleanUpStep())
                .end()
                //.listener(authorBatchService.getJobExecutionListener())
                .build();
    }

    private Step cleanUpStep() {
        return this.stepBuilderFactory.get("cleanUpStep")
                .tasklet(cleanUpTasklet())
                .build();
    }

    private MethodInvokingTaskletAdapter cleanUpTasklet() {
        MethodInvokingTaskletAdapter adapter = new MethodInvokingTaskletAdapter();
        //adapter.setTargetObject(cleanUpService);
        adapter.setTargetMethod("cleanUp");
        return adapter;
    }

    private Step transformBookStep() {
        return stepBuilderFactory.get("BookStep")
                .<Book, ru.otus.homework.domain.relational.book.Book>chunk(CHUNK_SIZE)
                .reader(readerBook())
                .processor(convertBookProcessor())
                .writer(writerBook())
                .build();
    }

    @StepScope
    private MongoItemReader<Book> readerBook() {
        return new MongoItemReaderBuilder<Book>()
                .name("BookReader")
                .template(mongoTemplate)
                .jsonQuery("{}")
                .collection("book")
                .targetType(Book.class)
                .sorts(Collections.emptyMap())
                .build();
    }

    private Step transformGenreStep() {
        return null;
    }

    private Step transformAuthorStep() {
        return null;
    }
}
