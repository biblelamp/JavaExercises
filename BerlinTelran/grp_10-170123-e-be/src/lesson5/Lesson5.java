package lesson5;

import java.util.*;

public class Lesson5 {

    static String text = "Lorem ipsum dolor sit amet consectetur adipiscing elit sed do eiusmod tempor incididunt ut labore et dolore magna aliqua Ut enim ad minim veniam quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur Excepteur sint occaecat cupidatat non proident sunt in culpa qui officia deserunt mollit anim id est laborum";

    public static void main(String[] args) {
        /*
        List<Integer> list = new ArrayList<>();
        list.add(2023);
        list.add(2022);
        System.out.println(list);
        list.add(1, 4);
        System.out.println(list);
        list.remove(1);
        list.add(2022);
        System.out.println(list);

        Set<Integer> set = new HashSet<>(list);
        System.out.println(set);
        String[] words = text.toLowerCase().split(" ");
        System.out.println(Arrays.toString(words));
         */
        Map<String, Integer> house = new HashMap<>();
        house.put("John", 1);
        house.put("Joanna", 1);
        house.put("Mike", 2);
        house.put("Bill", 3);
        house.put("TJ", 3);
        System.out.println(house);
        System.out.println(house.get("mike"));

        Map<Integer, List<String>> app = new HashMap<>();
        app.put(1, Arrays.asList("John", "Joanna"));
        app.put(2, Arrays.asList("Mike"));
        app.put(3, Arrays.asList("Bill", "TJ"));
        System.out.println(app);
        System.out.println(app.get(3));
    }
}
