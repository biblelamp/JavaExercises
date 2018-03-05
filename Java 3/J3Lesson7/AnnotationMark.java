import java.lang.reflect.Method;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class AnnotationMark {
    public static void main(String[] args) {
        Method[] methods = AnnotationMark.class.getDeclaredMethods();
        for (Method o : methods) {
            if (o.getAnnotation(MarkingAnnotation.class) != null) {
                System.out.println(o);
            }
        }
    }

    @MarkingAnnotation
    public void markedMethod() { }
    
    public void unmarkedMethod() { }
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface MarkingAnnotation {}