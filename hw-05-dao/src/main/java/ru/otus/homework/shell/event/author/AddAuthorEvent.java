package ru.otus.homework.shell.event.author;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.Map;

public class AddAuthorEvent extends ApplicationEvent {
    @Getter
    private final String fullName;

    public AddAuthorEvent(String fullName) {
        super(Map.of("fullName", fullName));
        this.fullName = fullName;

    }
}
