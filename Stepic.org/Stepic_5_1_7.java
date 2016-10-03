import java.io.*;

class Stepic_5_1_7 {

    public static void main(String[] args) {
        File f1 = new File(".\\a\\b\\..\\b\\c\\.\\file.txt");
        File f2 = new File("a\\b\\c\\file.txt");
        File f3 = new File("a\\.\\b\\..\\c\\.\\file.txt");
        File f4 = new File("a\\..\\b\\c\\file.txt");
        File f5 = new File(" a\\b\\..\\file.txt");
        try {
            System.out.println(f1.getCanonicalPath());
            System.out.println(f2.getCanonicalPath());
            System.out.println(f3.getCanonicalPath());
            System.out.println(f4.getCanonicalPath());
            System.out.println(f5.getCanonicalPath());
        } catch (Exception e) { 
            e.printStackTrace();
        }
    }
}