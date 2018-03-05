/**
 * Java. Level 3. Lesson 7
 * Explore the capabilities of class Class
 *
 * @see https://docs.oracle.com/javase/8/docs/api/java/lang/Class.html
 */
public class GetClassName {

    public static void main(String[] args) {

        // using method getClass()
        Integer i = 10;
        Class intClass = i.getClass();
        System.out.println("Full name: " + intClass.getName());
        System.out.println("Short name: " + intClass.getSimpleName());

        // get object Class directly
        Class floatClass = Float.class;
        System.out.println("Full name: " + floatClass.getName());
        System.out.println("Short name: " + floatClass.getSimpleName());
        
        // using method forName()
        try {
            Class strClass = Class.forName("java.lang.String");
            System.out.println("Full name: " + strClass.getName());
            System.out.println("Short name: " + strClass.getSimpleName());
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}