import java.lang.annotation.*;
import java.lang.reflect.*;
 
public class AnnotationParsing {
 
    public static void main(String[] args) {
        try {
            for (Method method : AnnotationParsing.class
                    .getClassLoader()
                    .loadClass(("ExampleAnnotation"))
                    .getMethods()) {
                // is there a MethodInfo annotation in the method?
                if (method.isAnnotationPresent(MethodInfo.class)) {
                    try {
                        // pass on all available annotations in the method
                        for (Annotation annot : method.getDeclaredAnnotations()) {
                            System.out.println("Annotations in method '" + method + "' : " + annot);
                        }
                        MethodInfo methodAnno = method
                                .getAnnotation(MethodInfo.class);
                        if (methodAnno.revision() == 1) {
                            System.out.println("method with revision #1 = " + method);
                        }
                    } catch (Throwable ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } catch (SecurityException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}