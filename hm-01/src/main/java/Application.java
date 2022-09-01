import exception.QuestionNotFoundException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.TestService;
import service.TestServiceImpl;

public class Application {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");

        TestService testService = context.getBean(TestServiceImpl.class);
        try {
            testService.runTest();
        } catch (QuestionNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
