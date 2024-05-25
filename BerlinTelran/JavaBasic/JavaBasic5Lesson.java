import java.util.Random;
import java.util.Scanner;

class JavaBasic5Lesson {
    public static void main(String[] args) {
        String str = new String("Hello,");
        String strJava = " Java!";
        String strResult = str.concat(strJava);
        System.out.println(strResult);
        System.out.println(strResult.contains("abc"));
        System.out.println(strResult.indexOf("Java"));
        System.out.println(str.equalsIgnoreCase("hello,"));
        System.out.println(strResult.length());
        System.out.println(strResult.substring(7, 11));
        System.out.println(strResult.toUpperCase());
        strResult += " Hi!";
        System.out.println(strResult);
        for (int i = 0; i < str.length(); i++) {
            System.out.print(str.charAt(i) + "-");
        }
        System.out.println(Math.PI);
    }
}