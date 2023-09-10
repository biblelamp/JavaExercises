package lesson15;

public class Lesson15 {
    public static void main(String[] args) {
        HashTable<String, String> hashTable = new HashTable<>(4);
        hashTable.put("Kiev", "Ukraine");
        hashTable.put("Kiev", "Ukraine1");
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
}
