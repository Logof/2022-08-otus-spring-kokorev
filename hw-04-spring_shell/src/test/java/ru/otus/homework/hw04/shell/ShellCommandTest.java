package ru.otus.homework.hw04.shell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.CommandNotCurrentlyAvailable;
import org.springframework.shell.Shell;
import ru.otus.homework.hw04.entity.User;
import ru.otus.homework.hw04.service.MessageService;
import ru.otus.homework.hw04.service.UserService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@DisplayName("Тест команд shell ")
@SpringBootTest
public class ShellCommandTest {

    @Autowired
    private Shell shell;

    @Autowired
    private MessageService messageService;

    @MockBean
    private UserService userService;

    private static final String COMMAND_LOGIN = "login";

    private static final String COMMAND_TEST_RUN = "test";

    private static final String COMMAND_LOGOUT = "logout";

    private static final String SHELL_MESSAGE_COMMAND_ERROR = "Command 't' exists but is not currently available because ";

    private User testUser;

    @DisplayName("Не должно запускаться тестирование если не залогинился")
    @Test
    void shouldNotStartTestingIfNotLoggedUser() {
        given(userService.registerNewUser()).willReturn(null);
        CommandNotCurrentlyAvailable res = (CommandNotCurrentlyAvailable) shell.evaluate(() -> COMMAND_TEST_RUN);
        assertThat(res.getMessage()).isEqualTo(SHELL_MESSAGE_COMMAND_ERROR +
                messageService.getMessage("user.promt.notlogin"));
    }


    @DisplayName("должна запускаться регистрация нового пользователя")
    @Test
    void shouldReturnExpectedGreetingAfterLoginCommandEvaluated() {
        testUser = new User();
        testUser.setSurname("TestSurname");
        testUser.setName("TestName");

        given(userService.registerNewUser()).willReturn(testUser);

        String res = (String) shell.evaluate(() -> COMMAND_LOGIN);

        assertThat(res).isEqualTo(String.format(messageService.getMessage("user.promt.welcome"),
                testUser.getName(), testUser.getSurname()));

    }

    @DisplayName(" logout")
    @Test
    void shouldReturnExpectedGreetingAfterLoginCommandEvaluated1() {
        String res = (String) shell.evaluate(() -> COMMAND_LOGOUT);
        assertThat(res).isEqualTo(messageService.getMessage("user.promt.goodbuy"));

    }
}
