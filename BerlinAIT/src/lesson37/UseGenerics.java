package lesson37;

import java.util.ArrayList;
import java.util.List;

public class UseGenerics {
    public static void main(String[] args) {
        RubberArray rb = new RubberArray();
        rb.add(34);
        rb.add(18);
        rb.add(3);
        rb.add(112);
        System.out.println(rb);
        System.out.println(rb.get(1));
        rb.remove(1);
        System.out.println(rb);

        GenericRubberArray<String> strList = new GenericRubberArray<>();
        strList.add("Hello");
        strList.add("Java");
        strList.add("Generics");
        System.out.println(strList);
        System.out.println(strList.get(2));
        strList.remove(1);
        System.out.println(strList);
    }
}
