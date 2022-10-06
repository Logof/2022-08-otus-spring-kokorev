package ru.otus.homework.shell.command;

import lombok.Getter;
import lombok.Setter;
import org.springframework.shell.Availability;
import org.springframework.stereotype.Component;
import ru.otus.homework.shell.publisher.EventsPublisher;

@Getter
@Component
public class CommonShell {
    private final EventsPublisher eventsPublisher;

    @Setter
    private String currBook;

    public CommonShell(EventsPublisher eventsPublisher) {
        this.eventsPublisher = eventsPublisher;
    }

    public Availability isEmptyIsbn() {
        return currBook == null
                ? Availability.unavailable("You need to select a book. Command: output-books AND select-book")
                : Availability.available();
    }
}
