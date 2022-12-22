package ru.otus.homework.hw14.shell;

import lombok.SneakyThrows;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.homework.hw14.config.JobConfig;

@ShellComponent
public class BookCommands {
    private final JobOperator jobOperator;

    public BookCommands(JobOperator jobOperator) {
        this.jobOperator = jobOperator;
    }

    @SneakyThrows
    @ShellMethod(value = "start migration", key = "start")
    public String startMigration() {
        Long executionId = jobOperator.startNextInstance(JobConfig.IMPORT_H2_TO_MONGO_JOB_NAME);
        return jobOperator.getSummary(executionId);
    }
}
