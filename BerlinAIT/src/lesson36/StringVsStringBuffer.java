package lesson36;

public class StringVsStringBuffer {
    public static void main(String[] args) {
        String s = "";
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100_000; i++) {
            s += "x";
        }
        System.out.println("String: " + (System.currentTimeMillis() - start));

        StringBuffer sb = new StringBuffer();
        start = System.currentTimeMillis();
        for (int i = 0; i < 100_000; i++) {
            sb.append("x");
        }
        System.out.println("StringBuffer: " + (System.currentTimeMillis() - start));
    }
}
