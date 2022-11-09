package ru.otus.homework.hw06.shell;

import lombok.Getter;
import lombok.Setter;
import org.springframework.shell.Availability;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public abstract class CommonShell {
    @Setter
    @Getter
    private static String currBook;

    public Availability isEmptyIsbn() {
        return Objects.isNull(currBook)
                ? Availability.unavailable("You need to select a book. Command: output-books AND select-book")
                : Availability.available();
    }
}
