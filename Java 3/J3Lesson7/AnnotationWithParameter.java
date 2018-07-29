import java.lang.reflect.Method;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class AnnotationWithParameter {

    public static void main(String[] args) {
        Method method;
        AdvencedAnnotation annotation;
        try {
            method = 
                AnnotationWithParameter.class.getMethod("firstMethod");
            annotation =
                method.getAnnotation(AdvencedAnnotation.class);
            System.out.println("value: " + annotation.value());
            method = 
                AnnotationWithParameter.class.getMethod("secondMethod");
            annotation =
                method.getAnnotation(AdvencedAnnotation.class);
            System.out.println("value: " + annotation.value());
        } catch (NoSuchMethodException ex) {
            ex.printStackTrace();
        }
    }

    @AdvencedAnnotation(value = 20.0f)
    public void firstMethod() { }

    @AdvencedAnnotation
    public void secondMethod() { }
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface AdvencedAnnotation {
    float value() default 5.0f;
}