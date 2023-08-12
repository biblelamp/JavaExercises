import java.util.Arrays;

class ExceptionExercises {
    public static void main(String[] args) {
        divizionByZero();
    }

    static void divizionByZero() {
        int a = 0;
        int b = 1;
        try {
            int c = b/a;
        } catch (Exception ex) {
            System.out.println(Arrays.toString(ex.getStackTrace()));
            System.out.println(ex.getMessage());
            System.out.println(ex);
            ex.printStackTrace();
        }
    }
}