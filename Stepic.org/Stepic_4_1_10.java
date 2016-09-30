import java.math.*;
import java.util.Arrays;

public class Stepic_4_1_10 {

    public static void main(String[] args) {
        System.out.println(getCallerClassAndMethodName());
        anotherMethod();
    }

    static void anotherMethod() {
        System.out.println(getCallerClassAndMethodName());
        secondMethod();
    }

    static void secondMethod() {
        System.out.println(getCallerClassAndMethodName());
    }

    /*
     * Implement a method that helps the other methods to find out where they were called
     */

    static String getCallerClassAndMethodName() {
        StackTraceElement[] stackTraceElements = new Exception().getStackTrace();
        if (stackTraceElements.length > 2) {
            StackTraceElement callerElement = stackTraceElements[2];
            return callerElement.getClassName() + "#" + callerElement.getMethodName();
        }
        return null;
    }
}