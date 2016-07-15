/**
 * Java. Level 2. Lesson 3. Homework
 *  1. Create a string with a small text (20-30 words, should be repeated words)
 *  a) Calculate how many times you see each word;
 *  b) Make a list of words that are in the text (duplicates don't calculate);
 *  2. Write a simple class PhoneBook
 *  a) Each record has three fields: Name, Phone, Email;
 *  b) Write methods to search for a phone by name and an email by name;
 *
 * @author Sergey Iryupin
 * @version 15 July 2016
 */
import java.util.*;

public class Lesson3 {

    public static void main(String[] args) {

        System.out.println("\nHomework. Stage 1\n");

        String text = "If you really want to learn, and you want to learn more quickly and more deeply, pay attention to how you pay attention. Think about how you think. Learn how you learn.";
        System.out.println(text);

        String[] words = text.toLowerCase().split("[ {,|.}?]+");
        System.out.println(Arrays.toString(words));

        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        Set<Map.Entry<String, Integer>> set = hm.entrySet();
        for (String word : words) {
            int value = 0;
            try {
                value = hm.get(word);
            } catch(NullPointerException e) { }
            hm.put(word, value + 1);
        }

        for (Map.Entry<String, Integer> o : set) {
            System.out.println(o.getKey() + ": " + o.getValue());
        }
        System.out.println("The phrase has " + hm.size() + " non-repeating words.");

        System.out.println("\nHomework. Stage 2 PhoneBook\n");

        PhoneBook pb = new PhoneBook();
        pb.go();
    }
}