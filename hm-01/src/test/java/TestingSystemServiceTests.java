import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.logof.entity.Answer;
import ru.otus.logof.entity.Question;
import ru.otus.logof.service.TestingSystemServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestingSystemServiceTests {
    @DisplayName("корректно создаётся объект TestService без корректного указания файла")
    @Test
    public void shouldHaveCorrectTestServiceConstructor() {
        TestingSystemServiceImpl testServiceExpected = new TestingSystemServiceImpl("");
        List<Question> questionListExpected =  testServiceExpected.getQuestionList();
        assertEquals(questionListExpected, new ArrayList<Question>());
    }

    @DisplayName("корректно создаётся объект TestService")
    @Test
    public void shouldHaveCorrectTestServiceConstructorWithFile() {
        TestingSystemServiceImpl testServiceExpected = new TestingSystemServiceImpl("/test.csv");
        List<Question> questionListExpected =  testServiceExpected.getQuestionList();

        List<Question> questionListActual =  testServiceExpected.getQuestionList();
        List<Answer> answerList = new ArrayList<>();
        answerList.add(new Answer("Answer 1", true));
        answerList.add(new Answer("Answer 2", false));
        answerList.add(new Answer("Answer 3", false));

        questionListActual.add(new Question("Question 1", answerList));
        assertEquals(questionListExpected, questionListActual);
    }
}
