/**
 * Java. Level 3. Lesson 7. Homework
 *
 * @author Sergey Iryupin
 * @version Jul 30, 2018
 * @link https://github.com/biblelamp
 */
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HW7Lesson {

    public static void main(String[] args) {
        TestingClass.start(TestingClass.class);
    }
}

class TestingClass {

    public static void start(Class cls) {
        performTests(cls);
    }

    private static void performTests(Class cls) throws RuntimeException {
        TestingClass testingObj = null;
        try {
            testingObj = (TestingClass)cls.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        Method beforeMethod = null;
        Method afterMethod = null;
        Method[] methods = cls.getMethods();
        List<MethodWithPriority> testingMethods = new ArrayList<>();

        for (Method method : methods)
            if (method.getAnnotation(BeforeSuite.class) != null) {
                if (beforeMethod != null)
                    throw new RuntimeException("The method with the @BeforeSuite annotation should be one");
                beforeMethod = method;
            } else if (method.getAnnotation(AfterSuite.class) != null) {
                if (afterMethod != null)
                    throw new RuntimeException("The method with the @AfterSuite annotation should be one");
                afterMethod = method;
            } else if (method.getAnnotation(Test.class) != null){
                Test annotationTst = method.getAnnotation(Test.class);
                testingMethods.add(new MethodWithPriority(method, annotationTst.value()));
            }

        testingMethods.sort(
            Comparator.comparing(MethodWithPriority::getPriority));

        try {
            if (beforeMethod != null)
                beforeMethod.invoke(testingObj);

            for (MethodWithPriority methodWP : testingMethods)
                methodWP.method.invoke(testingObj);

            if (afterMethod != null)
                afterMethod.invoke(testingObj);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test(value = 5)
    public void Test5() {
        System.out.println("Performed Test №5");
    }
    @Test(value = 3)
    public void Test2(){
        System.out.println("Performed Test №2");
    }
    @Test(value = 1)
    public void Test1(){
        System.out.println("Performed Test №1");
    }
    @BeforeSuite
    public void BeforeAll(){
        System.out.println("This method is performed before all");
    }
    @AfterSuite
    public void AfterAll() {
        System.out.println("This method is performed after all");
    }
}

class MethodWithPriority {
    public Method method;
    public Integer priority;

    public MethodWithPriority(Method method, int priority) {
        this.method = method;
        this.priority = priority;
    }

    public Integer getPriority() {
        return priority;
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface BeforeSuite {}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface AfterSuite {}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Test {
    int value();
}