package ru.otus.homework.hw14.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
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
import ru.otus.homework.hw14.entity.h2.Author;
import ru.otus.homework.hw14.entity.h2.Book;
import ru.otus.homework.hw14.entity.h2.Comment;
import ru.otus.homework.hw14.entity.h2.Genre;
import ru.otus.homework.hw14.entity.mongo.AuthorDocument;
import ru.otus.homework.hw14.entity.mongo.BookDocument;
import ru.otus.homework.hw14.entity.mongo.CommentDocument;
import ru.otus.homework.hw14.entity.mongo.GenreDocument;
import ru.otus.homework.hw14.mapper.EntityToDocumentConverter;
import ru.otus.homework.hw14.repository.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Configuration
public class JobConfig {
    private static final int CHUNK_SIZE = 5;

    public static final String IMPORT_H2_TO_MONGO_JOB_NAME = "importH2ToMongoJob";

    private final Map<Author, AuthorDocument> authorMap = new ConcurrentHashMap<>();

    private final Map<Genre, GenreDocument> genreMap = new ConcurrentHashMap<>();

    private final Map<String, BookDocument> bookMap = new ConcurrentHashMap<>();

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @StepScope
    @Bean
    public RepositoryItemReader<Author> authorReader(AuthorRepository h2AuthorRepository) {
        RepositoryItemReader<Author> reader = new RepositoryItemReader<>();
        reader.setRepository(h2AuthorRepository);
        reader.setMethodName("findAll");
        reader.setSort(new HashMap<>());
        return reader;
    }

    @StepScope
    @Bean
    public RepositoryItemReader<Genre> genreReader(GenreRepository h2GenreRepository) {
        RepositoryItemReader<Genre> reader = new RepositoryItemReader<>();
        reader.setRepository(h2GenreRepository);
        reader.setMethodName("findAll");
        reader.setSort(new HashMap<>());
        return reader;
    }

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
    public ItemProcessor<Author, AuthorDocument> authorProcessor(EntityToDocumentConverter converter) {
        return (ItemProcessor<Author, AuthorDocument>) converter::authorTransformation;
    }

    @StepScope
    @Bean
    public ItemProcessor<Genre, GenreDocument> genreProcessor(EntityToDocumentConverter converter) {
        return (ItemProcessor<Genre, GenreDocument>) converter::genreTransformation;
    }

    @StepScope
    @Bean
    public ItemProcessor<Book, BookDocument> bookProcessor(EntityToDocumentConverter converter) {
        return (ItemProcessor<Book, BookDocument>) converter::bookTransformation;
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
    public RepositoryItemWriter<AuthorDocument> authorWriter(MongoAuthorRepository authorRepository) {
        RepositoryItemWriter writer = new RepositoryItemWriter<>();
        writer.setRepository(authorRepository);
        writer.setMethodName("save");
        return writer;
    }

    @Bean
    public RepositoryItemWriter<GenreDocument> genreWriter(MongoGenreRepository genreRepository) {
        RepositoryItemWriter writer = new RepositoryItemWriter<>();
        writer.setRepository(genreRepository);
        writer.setMethodName("save");
        return writer;
    }


    @Bean
    public Step step0MigrateAuthors(RepositoryItemReader<Author> authorReader,
                                    ItemProcessor<Author, AuthorDocument> authorProcessor,
                                    RepositoryItemWriter<AuthorDocument> authorWriter) {
        return stepBuilderFactory.get("step0_MigrateAuthors")
                .<Author, AuthorDocument>chunk(CHUNK_SIZE)
                .reader(authorReader)
                .processor(authorProcessor)
                .writer(authorWriter)
                .listener(new ItemProcessListener<Author, AuthorDocument>() {
                    @Override
                    public void beforeProcess(Author author) {

                    }

                    @Override
                    public void afterProcess(Author author, AuthorDocument authorDocument) {
                        authorMap.put(author, authorDocument);
                    }

                    @Override
                    public void onProcessError(Author author, Exception e) {

                    }
                })

                .build();
    }

    @Bean
    public Step step0MigrateGenres(RepositoryItemReader<Genre> genreReader,
                                    ItemProcessor<Genre, GenreDocument> genreProcessor,
                                    RepositoryItemWriter<GenreDocument> genreWriter) {
        return stepBuilderFactory.get("step0_MigrateAuthors")
                .<Genre, GenreDocument>chunk(CHUNK_SIZE)
                .reader(genreReader)
                .processor(genreProcessor)
                .writer(genreWriter)
                .listener(new ItemProcessListener<Genre, GenreDocument>() {
                    @Override
                    public void beforeProcess(Genre genre) {

                    }

                    @Override
                    public void afterProcess(Genre genre, GenreDocument genreDocument) {
                        genreMap.put(genre, genreDocument);
                    }

                    @Override
                    public void onProcessError(Genre genre, Exception e) {

                    }
                })
                .build();
    }



    @Bean
    public Step step1MigrateBooks(RepositoryItemReader<Book> bookReader,
                                 ItemProcessor<Book, BookDocument> bookProcessor,
                                 RepositoryItemWriter<BookDocument> bookWriter) {
        return stepBuilderFactory.get("step1_MigrateBooks")
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
    public Step step2MigrateComments(RepositoryItemReader<Comment> commentReader,
                                    ItemProcessor<Comment, CommentDocument> commentProcessor,
                                    RepositoryItemWriter<CommentDocument> commentWriter
    ) {
        return stepBuilderFactory.get("step2_MigrateComments")
                .<Comment, CommentDocument>chunk(CHUNK_SIZE)
                .reader(commentReader)
                .processor(commentProcessor)
                .writer(commentWriter)
                .build();
    }

    @Bean
    public Job importH2ToMongoJob(Step step0MigrateAuthors, Step step1MigrateBooks, Step step2MigrateComments) {
        return jobBuilderFactory.get(IMPORT_H2_TO_MONGO_JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .start(step0MigrateAuthors)
                .next(step1MigrateBooks)
                .next(step2MigrateComments)
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
