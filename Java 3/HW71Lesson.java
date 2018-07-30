/**
 * Java. Level 3. Lesson 7. Homework
 * 1. Create a method-researcher. At the input of the class name,
 *    the method outputs to the console all the information about the class
 *    that can be obtained using Reflection
 *
 * @author Sergey Iryupin
 * @version Mar 09, 2018
 * @link https://github.com/biblelamp
 */
import java.lang.reflect.Field;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class HW71Lesson {

    public static void main(String[] args) {
        HW71Lesson hw = new HW71Lesson();
        hw.getClassInformation(
            (args.length == 0)? "java.lang.String" : args[0]);
    }

    void getClassInformation(String className) {
        Class classObj;
        try {
            classObj = Class.forName(className);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return;
        }
        // get full and short names
        System.out.println("Full name: " + classObj.getName());
        System.out.println("Short name: " + classObj.getSimpleName());

        // get full and short names of superclass
        System.out.println("Parent full name: " +
                classObj.getSuperclass().getName());
        System.out.println("Parent short name: " +
                classObj.getSuperclass().getSimpleName());

        // get public fields of class
        System.out.println("\nPublic fields:");
        for (Field field : classObj.getFields())
            System.out.println(
                field.getType().getName() + " " + field.getName());

        // get public constructors
        System.out.println("\nPublic constructors:");
        for (Constructor constructor : classObj.getConstructors())
            System.out.println(constructor);

        // get public methods
        System.out.println("\nPublic methods:");
        for (Method method : classObj.getMethods())
            System.out.println(method);
    }
}