/**
 * Java. Level 2. Lesson 2. Exception
 * Examples for lesson
 *
 * @author Sergey Iryupin
 * @version dated Jun 10, 2017
 */
class J2Lesson2 {

    public static void main(String[] args) {
        try {
            justMethod();
        } catch (NullPointerException ex) {
            System.out.println(ex);
        }
        System.out.println("Main done!");
    }

    static void justMethod() throws NullPointerException {
        System.out.println("We will try...");
        int a = 0;
        int[] arr = new int[5];
        try {
            //arr[5] = 12;
            //int b = 10 / a;
            throw new NullPointerException("NPE MY exception");
        } catch (ArithmeticException ex) {
            System.out.println(ex);
            //System.exit(0);
            //return;
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println(ex);
            //System.exit(0);
            //return;
        //} catch (Exception ex) {
        //    System.out.println(ex);
        }
        finally {
            System.out.println("Finally");
        }
        System.out.println("Done!");
    }
}