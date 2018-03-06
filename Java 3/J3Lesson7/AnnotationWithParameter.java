import java.lang.reflect.Method;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class AnnotationWithParameter {

    public static void main(String[] args) {
        try {
            Method method = 
                AnnotationWithParameter.class.getMethod("firstMethod", null);
            AdvencedAnnotation annotation =
                method.getAnnotation(AdvencedAnnotation.class);
            System.out.println("value: " + annotation.value());
        } catch (NoSuchMethodException ex) {
            ex.printStackTrace();
        }
    }

    @AdvencedAnnotation(value = 20.0f)
    public void firstMethod() { }

    public void thirdMethod() { }
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface AdvencedAnnotation {
    float value() default 5.0f;
}