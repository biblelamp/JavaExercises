import java.time.*;

class CatchError {
    public static void main(String[] args) {
        try {
            //int a = 1/0;
            Duration.between(null, LocalDateTime.now()).toSeconds();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            System.out.println("finally");
        }

        LocalDate dt = LocalDate.now();
        System.out.println(dt.toString());

        for (int i = 1; i < 13; i++) {
            System.out.println(i + ": " + ((i-1)/3+1));
        }
    }
}