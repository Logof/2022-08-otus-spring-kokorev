package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework.entity.Answer;
import ru.otus.homework.entity.Question;
import ru.otus.homework.enums.QuestionTypeEnum;
import ru.otus.homework.exeption.IncorrectNumberAnswerException;
import ru.otus.homework.repository.QuestionsRepositoryCsv;
import ru.otus.homework.service.FileReader;
import ru.otus.homework.service.IOServiceImpl;
import ru.otus.homework.service.TestingSystemServiceImpl;
import ru.otus.homework.service.UserServiceImpl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TestingSystemServiceImplTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Mock
    private IOServiceImpl ioService;

    @Mock
    private InputStream scanner;

    @Mock
    private FileReader questionFileReader;

    @Mock
    QuestionsRepositoryCsv questionsRepository;

    @Mock
    UserServiceImpl userService;

    @BeforeEach
    public void setup() throws IOException {
        System.setOut(new PrintStream(outputStreamCaptor));
        //ioService = new IOServiceImpl(new PrintStream(outputStreamCaptor), scanner);
    }


    private Method getcheckingUserAnswersMethod() throws NoSuchMethodException {
        Method method = TestingSystemServiceImpl.class.getDeclaredMethod("checkingUserAnswers", Question.class, List.class);
        method.setAccessible(true);
        return method;
    }

    @Test
    public void Test1() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IncorrectNumberAnswerException {
        List<Question> questionList = new ArrayList<>();
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer("Answer1", true));
        answers.add(new Answer("Answer2", false));
        Question question = new Question("Quest1", QuestionTypeEnum.CHOICE_ANSWERS, answers);
        questionList.add(question);

        List<Answer> answersActual = new ArrayList<>();
        answersActual.add(new Answer("Answer1", true));

        List<Answer> answersExpected = new ArrayList<>();
        answersExpected.add(new Answer("Answer1", true));

        Method method = TestingSystemServiceImpl.class.getDeclaredMethod("checkingUserAnswers", Question.class, List.class);
        method.setAccessible(true);
        System.out.println(method.trySetAccessible());
        var result = method.invoke(questionList, answersExpected);
        assertEquals(method.invoke(questionList, answersExpected), true);

    }
}
