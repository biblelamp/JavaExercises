package lesson28;

/**
 * AIT-TR, cohort 42.1, Java Basic, Lesson #28
 *
 * @version 18-Mar-24
 */
public class Lesson28 {
    public static void main(String[] args) {
        OuterAndInnerClass outerAndInnerClass = new OuterAndInnerClass();
        outerAndInnerClass.useInnerClass();

        OuterAndInnerStaticClass outerAndInnerStaticClass = new OuterAndInnerStaticClass();
        outerAndInnerStaticClass.useInnerClass();

        //OuterAndInnerClass.InnerClass innerClass = new OuterAndInnerClass.InnerClass();
        OuterAndInnerStaticClass.InnerClass innerStaticClass = new OuterAndInnerStaticClass.InnerClass();
    }
}
