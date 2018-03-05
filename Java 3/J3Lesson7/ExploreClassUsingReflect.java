/**
 * Java. Level 3. Lesson 7
 * Explore class using Class && java.lang.reflect
 *
 * @see https://docs.oracle.com/javase/8/docs/api/java/lang/Class.html
 * @see https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/Modifier.html
 * @see https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/Field.html
 * @see https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/Constructor.html
 * @see https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/Method.html
 */
import java.lang.reflect.Modifier;
import java.lang.reflect.Field;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ExploreClassUsingReflect {

    public static void main(String[] args) {
        Class catClass = Cat.class;
        int modifiers = catClass.getModifiers();

        // get modifiers of class
        if (Modifier.isPublic(modifiers))
            System.out.println(catClass.getSimpleName() + " - public");
        if (Modifier.isAbstract(modifiers))
            System.out.println(catClass.getSimpleName() + " - abstract");
        if (Modifier.isFinal(modifiers))
            System.out.println(catClass.getSimpleName() + " - final");

        // get public fields of class
        for (Field field : catClass.getFields())
            System.out.println(
            "Type name: " + field.getType().getName() + " " + field.getName());

        // get public constructors
        for (Constructor constructor : catClass.getConstructors())
            System.out.println(constructor);

        // get public methods
        for (Method method : catClass.getMethods())
            System.out.println(method);
    }
}