package ru.otus.homework.hw14.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemWriteListener;
import ru.otus.homework.hw14.entity.h2.Author;

import java.util.List;

@Slf4j
public class AuthorWriteListener implements ItemWriteListener<Author> {
    @Override
    public void beforeWrite(List<? extends Author> list) {

    }

    @Override
    public void afterWrite(List<? extends Author> list) {
        log.info("{}", list);
    }

    @Override
    public void onWriteError(Exception e, List<? extends Author> list) {

    }
}
