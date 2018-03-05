/**
 * Java. Level 3. Lesson 7
 * Explore the capabilities of class Class
 *
 * @see https://docs.oracle.com/javase/8/docs/api/java/lang/Class.html
 * @see https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/Modifier.html
 */
import java.lang.reflect.Modifier;

public class GetModifiersOfClass {

    public static void main(String[] args) {

        Class strClass = String.class;
        int modifiers = strClass.getModifiers();

        if (Modifier.isPublic(modifiers))
            System.out.println(strClass.getSimpleName() + " - public");

        if (Modifier.isAbstract(modifiers))
            System.out.println(strClass.getSimpleName() + " - abstract");

        if (Modifier.isFinal(modifiers))
            System.out.println(strClass.getSimpleName() + " - final");
    }
}