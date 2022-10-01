package ru.otus.homework.shell;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.homework.entity.User;
import ru.otus.homework.service.MessageService;
import ru.otus.homework.service.TestingSystemService;
import ru.otus.homework.service.UserService;

@ShellComponent
public class ShellCommand {
    private User user = null;
    private final UserService userService;

    private final MessageService messageService;

    private final TestingSystemService testingSystemService;

    public ShellCommand(UserService userService, MessageService messageService,
                        TestingSystemService testingSystemService) {
        this.userService = userService;
        this.messageService = messageService;
        this.testingSystemService = testingSystemService;
    }

    private Availability isTestCommandAvailable() {
        return (user == null) ?
                Availability.unavailable(messageService.getMessage("user.promt.notlogin")) : Availability.available();
    }

    @ShellMethod(value = "Login command", key = {"l", "login"})
    public String login() {
        user = userService.registerNewUser();
        return String.format(messageService.getMessage("user.promt.welcome"),
                user.getName(), user.getSurname());
    }

    @ShellMethod(value = "Logout command", key = {"o", "logout"})
    public String logout() {
        user = null;
        return String.format(messageService.getMessage("user.promt.goodbuy"));
    }

    @ShellMethod(value = "Starting test", key = {"t", "test"})
    @ShellMethodAvailability(value = "isTestCommandAvailable")
    public void publishEvent() {
        testingSystemService.runTesting(user);
    }
}
