package lesson32.homework;

/**
 * AIT-TR, cohort 42.1, Java Basic, Homework #32 ext
 *
 * @author Sergey Iryupin
 * @version 4-Apr-24
 */
public class BoxUse {
    public static void main(String[] args) {
        Box<Apple> appleBox = new Box<>();
        Box<Orange> orangeBox = new Box<>();

        appleBox.add(new Apple());
        appleBox.add(new Apple());
        appleBox.add(new Apple());
        System.out.println(appleBox);
        System.out.println("Box weight: " + appleBox.getWeight());

        orangeBox.add(new Orange());
        orangeBox.add(new Orange());
        orangeBox.add(new Orange());
        System.out.println(orangeBox);
        System.out.println("Box weight: " + orangeBox.getWeight());

        // compare test
        System.out.println("Compare: " + appleBox.compareTo(orangeBox));
        System.out.println("Compare: " + orangeBox.compareTo(orangeBox));
        System.out.println("Compare: " + orangeBox.compareTo(appleBox));

        // moveTo test
        Box<Apple> newAppleBox = new Box<>();
        appleBox.moveTo(newAppleBox);
        System.out.println("appleBox: " + appleBox);
        System.out.println("newAppleBox: " + newAppleBox);
    }
}
