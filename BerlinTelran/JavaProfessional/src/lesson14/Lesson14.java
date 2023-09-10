package lesson14;

import java.util.HashMap;
import java.util.Map;

public class Lesson14 {
    public static void main(String[] args) {
        HashTable<String, String> hashTable = new HashTable<>();
        hashTable.put("Kiev", "Ukraine");
        hashTable.put("Paris", "France");
        hashTable.put("Moscow", "Russia");
        hashTable.put("Berlin", "Germany");
        hashTable.put("Prague", "Czech Republic");
        hashTable.put("Warsaw", "Poland");
        hashTable.put("Madrid", "Spain");
        System.out.println(hashTable.get("Kiev"));
        System.out.println(hashTable.get("Paris"));
        System.out.println(hashTable.get("Moscow"));
        System.out.println(hashTable.get("Berlin"));
        System.out.println(hashTable.get("Prague"));
        System.out.println(hashTable.get("Warsaw"));
        System.out.println(hashTable.get("Madrid"));
        System.out.println(hashTable);
    }

    static void before() {
        Map<String, String> map = new HashMap<>();
        map.put("Kiev", "Ukraine");
        map.put("Paris", "France");
        map.put("Moscow", "Russia");
        System.out.println(map);
        System.out.println(map.get("Berlin"));
        System.out.println(map.keySet());
        System.out.println(map.values());
        Integer integer = 1;
    }
}
