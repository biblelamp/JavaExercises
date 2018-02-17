/**
 * Java. Level 1. Lesson 7
 *
 * @author Sergey Iryupin
 * @version dated Feb 17, 2018
 */
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

class J1Lesson7 {
    public static void main(String[] args) {
        //Test.testString(120000);
        //Test.testStringBuilder(120000);
        //Test.testBufferedReader("E:\\Audacity\\audacity.exe");
        //Test.testFileReader("E:\\Audacity\\audacity.exe");
        Cat cat = new Cat("Barsik", 10);
        Plate plate = new Plate(5);
        System.out.println(plate);
        cat.eat(plate);
        System.out.println(plate);
    }
}

class Cat {
    private String name;
    private int appetite;

    Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
    }

    void eat(Plate plate) {
        plate.dicreaseFood(appetite);
    }
}

class Plate {
    private int food;

    Plate(int food) {
        this.food = food;
    }

    void dicreaseFood(int food) {
        this.food -= food;
    }

    @Override
    public String toString() {
        return "Food: " + food;
    }
}

class Test {
    /**
     * Testing class String (immutable)
     */
    static void testString(int cycles) {
        System.out.print("Testing String... ");
        long t1 = System.currentTimeMillis();

        String a = "";
        for (int i = 0; i < cycles; i++)
            a += "w";

        long t2 = System.currentTimeMillis();
        System.out.println("It took " + (t2 - t1) + " mc");
    }

    /**
     * Testing class StringBuilder (mutable)
     */
    static void testStringBuilder(int cycles) {
        System.out.print("Testing StringBuilder... ");
        long t1 = System.currentTimeMillis();

        StringBuilder a = new StringBuilder("");
        for (int i = 0; i < cycles; i++)
            a.append("w");

        long t2 = System.currentTimeMillis();
        System.out.println("It took " + (t2 - t1) + " mc");
    }

    /**
     * Testing class FileReader
     */
    static void testFileReader(String fileName) {
        System.out.print("Testing FileReader... ");
        long t1 = System.currentTimeMillis();

        try (FileReader file = new FileReader(fileName)) {
            int x = -1;
            do
                x = file.read();
            while (x != -1);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        long t2 = System.currentTimeMillis();
        System.out.println("It took " + (t2 - t1) + " mc");
    }

    /**
     * Testing class BufferedReader
     */
    static void testBufferedReader(String fileName) {
        System.out.print("Testing BufferedReader... ");
        long t1 = System.currentTimeMillis();

        try (BufferedReader file = new BufferedReader(
                new FileReader(fileName))) {
            int x = -1;
            do
                x = file.read();
            while (x != -1);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        long t2 = System.currentTimeMillis();
        System.out.println("It took " + (t2 - t1) + " mc");
    }
}