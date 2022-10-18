package ru.otus.homework.shell;

import lombok.Getter;
import lombok.Setter;
import org.springframework.shell.Availability;
import org.springframework.stereotype.Component;

@Getter
@Component
public class CommonShell {

    @Setter
    private String currBook;

    public Availability isEmptyIsbn() {
        return currBook == null
                ? Availability.unavailable("You need to select a book. Command: output-books AND select-book")
                : Availability.available();
    }
}
