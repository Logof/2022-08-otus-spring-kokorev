package ru.otus.homework.hw14.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ru.otus.homework.hw14.entity.mongo.Author;

import javax.batch.api.chunk.listener.ItemReadListener;
import java.util.List;

@Service
@Slf4j
public class AuthorBatchService {
    public ItemReadListener<Author> getItemReadListener() {
        return new ItemReadListener<>() {
            public void beforeRead() {
                log.info("Начало чтения");
            }

            @Override
            public void afterRead(Object o) throws Exception {
                log.info("Конец чтения");
            }

            public void onReadError(@NonNull Exception e) {
                log.info("Ошибка чтения");
            }
        };
    }

    public JobExecutionListener getJobExecutionListener() {
        return new JobExecutionListener() {
            @Override
            public void beforeJob(@NonNull JobExecution jobExecution) {
                log.info("Начало job");
            }

            @Override
            public void afterJob(@NonNull JobExecution jobExecution) {
                log.info("Конец job");
            }
        };
    }

    public ItemWriteListener<Author> getItemWriteListener() {
        return new ItemWriteListener<>() {
            public void beforeWrite(@NonNull List list) {
                log.info("Начало записи");
            }

            public void afterWrite(@NonNull List list) {
                log.info("Конец записи");
            }

            public void onWriteError(@NonNull Exception e, @NonNull List list) {
                log.info("Ошибка записи");
            }
        };
    }

    public ItemProcessListener<Author, Author> getItemProcessListener() {
        return new ItemProcessListener<>() {
            public void beforeProcess(Author o) {
                log.info("Начало обработки");
            }

            public void afterProcess(@NonNull Author o, Author o2) {
                log.info("Конец обработки");
            }

            public void onProcessError(@NonNull Author o, @NonNull Exception e) {
                log.info("Ошибка обработки");
            }
        };
    }

    public ChunkListener getChunkListener() {
        return new ChunkListener() {
            public void beforeChunk(@NonNull ChunkContext chunkContext) {
                log.info("Начало пачки");
            }

            public void afterChunk(@NonNull ChunkContext chunkContext) {
                log.info("Конец пачки");
            }

            public void afterChunkError(@NonNull ChunkContext chunkContext) {
                log.info("Ошибка пачки");
            }
        };
    }
}
