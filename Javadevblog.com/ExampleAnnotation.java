import java.io.*;
import java.util.*;
 
class ExampleAnnotation {

    public static void main(String[] args) {
    }

    @Override
    @MethodInfo(author = "Andrew", comments = "Main method", date = "Aug 10 2015", revision = 1)
    public String toString() {
        return "Override method toString()";
    }

    @Deprecated
    @MethodInfo(comments = "Outdated method", date = "Aug 10 2015")
    public static void oldMethod() {
        System.out.println("This method is not worth to use.");
    }
 
    @SuppressWarnings({"unchecked", "deprecation"})
    @MethodInfo(author = "Andrew", comments = "Main method", date = "Aug 10 2015", revision = 4)
    public static void genericsTest() throws FileNotFoundException {
        List l = new ArrayList();
        l.add("asdf");
        oldMethod();
    }
}