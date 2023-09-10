package lesson4;

import java.util.*;

public class Lesson4 {
    public static void main(String[] args) {
        List<String> list = new LinkedList<>();
        list.add("AAA");
        list.add("BB");
        list.add("C");
        list.add("DDDD");
        list.add(2, "BB");
        System.out.println(list);
        Set<String> set = new HashSet<>(list);
        System.out.println(set);
        Map<String, String> map = new HashMap<>();
        map.put("Prague", "CZ");
        map.put("Berlin", "DE");
        System.out.println(map);
        System.out.println(map.get("Brno"));
    }
}
