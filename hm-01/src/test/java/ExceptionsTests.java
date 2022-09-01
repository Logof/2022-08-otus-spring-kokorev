import exception.QuestionNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class ExceptionsTests {

    @DisplayName("корректно создаётся объект QuestionNotFoundException")
    @Test
    public void shouldHaveCorrectQuestionNotFoundException() {
        Exception expectedEx = assertThrows(QuestionNotFoundException.class, () -> { throw new QuestionNotFoundException();});
        assertTrue(expectedEx.getMessage().startsWith("Test question not found"));
    }

    @DisplayName("корректно создаётся объект QuestionNotFoundException с доп. сообщением")
    @Test
    public void shouldHaveCorrectQuestionNotFoundExceptionWithAdditionMessage() {
        Exception expectedEx = assertThrows(QuestionNotFoundException.class, () -> {
            throw new QuestionNotFoundException("additional message");
        });
        assertTrue(expectedEx.getMessage().startsWith("Test question not found: additional message"));
    }
}
