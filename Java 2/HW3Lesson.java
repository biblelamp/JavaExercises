/**
 * Java. Level 2. Lesson 3. Example of homework
 *
 * @author Sergey Iryupin
 * @version 0.3 dated Nov 04, 2017
 */
import java.util.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
//import hw3.*; // old variant homework

class HW3Lesson {

    static final String TEXT = "If you really want to learn, and you want " +
        "to learn more quickly and more deeply, pay attention to how you " +
        "pay attention. Think about how you think. Learn how you learn.";
    static final String FILE_NAME = "lesson3text.txt";

    public static void main(String[] args) throws IOException {

        // 1 task
        System.out.println("1. Counting words:");
        сountWords(TEXT);

        // read file to list
        List<String> lines =
            Files.readAllLines(Paths.get(FILE_NAME), StandardCharsets.UTF_8);

        // convert List to Array and Array to string
        String[] strings = new String[lines.size()];
        strings = lines.toArray(strings);
        сountWords(Arrays.toString(strings));

        // 2 task
        System.out.println("\n2. Phone book:");
        testPhoneBook();
    }

    static void сountWords(String text) {
        Integer value;
        Map<String, Integer> hm = new TreeMap<>(); // HashMap better for speed
        String[] words = text.toLowerCase().split("\\W+");
        for (String word : words) {
            value = hm.get(word);
            hm.put(word, ((value == null)? 1 : value + 1));
        }
        System.out.println(hm);
        System.out.println("The phrase has " + words.length + " words and " +
            hm.size() + " non-repeated words.");
    }

    static void testPhoneBook() {
        SimplePhoneBook spb = new SimplePhoneBook();
        spb.add("Mathew", "863 233 3301");
        spb.add("Mark", "863 212 1102");
        spb.add("Luke", "863 243 2803");
        spb.add("John", "863 240 2704");
        spb.add("Luke", "863 248 4512");
        System.out.println(spb);
        System.out.println("Phones of Luke's:");
        System.out.println(spb.get("Luke"));
        /*
        PhoneBook pb = new PhoneBook();
        pb.addRecord("John", new PhoneRecord("234-22-12", "john@mail.com"));
        pb.addRecord("Smith", new PhoneRecord("234-22-21", "smith@mail.com"));
        pb.addRecord("Mike", new PhoneRecord("233-12-12", "mike@mail.com"));
        pb.addPhone("Mike", "+1 234 567-89-00");
        System.out.println(pb.getPhonesByName("Mike")); // found
        System.out.println(pb.getPhonesByName("Bill")); // not found
        System.out.println(pb.getEmailsByName("Smith")); // found
        System.out.println(pb.getEmailsByName("Joseph")); // not found
        */
    }
}

class SimplePhoneBook {
    Map<String, String> pb;

    SimplePhoneBook() {
        pb = new HashMap<>();
    }

    void add(String name, String phone) {
        pb.put(phone, name);
    }

    List<String> get(String name) {
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, String> entry : pb.entrySet())
            if (name.equals(entry.getValue()))
                list.add(entry.getKey());
        return list;
    }

    @Override
    public String toString() {
        return pb.toString();
    }
}