package ru.otus.homework.hw05.shell;

import lombok.Getter;
import lombok.Setter;
import org.springframework.shell.Availability;

import java.util.Objects;

public abstract class CommonShell {

    @Setter
    @Getter
    private static String currBook;

    public Availability isEmptyIsbn() {
        return Objects.isNull(currBook)
                ? Availability.unavailable("You need to select a book. Command: print-books AND select-book")
                : Availability.available();
    }
}
