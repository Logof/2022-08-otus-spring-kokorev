import exception.QuestionNotFoundException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.TestingSystemService;
import service.TestingSystemServiceImpl;

public class Application {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");

        TestingSystemService testingSystemService = context.getBean(TestingSystemServiceImpl.class);
        try {
            testingSystemService.runTest();
        } catch (QuestionNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
