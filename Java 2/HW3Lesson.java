/**
 * Java. Level 2. Lesson 3. Example of homework
 *
 * @author Sergey Iryupin
 * @version 0.1.1 dated Jun 16, 2017
 */
import java.util.*;
import hw3.PhoneBook;
import hw3.PhoneRecord;

class HW3Lesson {

    public static void main(String[] args) {
        System.out.println("1. Counting words:");
        сountWords();
        System.out.println("2. Phone book:");
        testPhoneBook();
    }

    static void сountWords() {
        Integer value;
        Map<String, Integer> hm = new TreeMap<>();
        String text = "If you really want to learn, and you want to learn " +
        "more quickly and more deeply, pay attention to how you pay " +
        "attention. Think about how you think. Learn how you learn.";
        String[] words = text.toLowerCase().split("[ {,|.}?]+");
        for (String word : words) {
            value = hm.get(word);
            hm.put(word, ((value == null)? 1 : value + 1));
        }
        System.out.println(hm);
        System.out.println("The phrase has " + words.length + " words and " +
            hm.size() + " non-repeated words.");
    }

    static void testPhoneBook() {
        PhoneBook pb = new PhoneBook();
        pb.addRecord("John", new PhoneRecord("234-22-12", "john@mail.com"));
        pb.addRecord("Smith", new PhoneRecord("234-22-21", "smith@mail.com"));
        pb.addRecord("Mike", new PhoneRecord("233-12-12", "mike@mail.com"));
        pb.addPhone("Mike", "+1 234 567-89-00");
        System.out.println(pb.getPhonesByName("Mike")); // found
        System.out.println(pb.getPhonesByName("Bill")); // not found
        System.out.println(pb.getEmailsByName("Smith")); // found
        System.out.println(pb.getEmailsByName("Joseph")); // not found
    }
}