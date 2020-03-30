import java.util.Arrays;

class SplitExp {
    public static void main(String[] args) {
        String str = "IF (COUNT-5)1.2,1.3,1.4";
        String[] part = str.split("[()]");
        System.out.println(Arrays.toString(part));
    }
}