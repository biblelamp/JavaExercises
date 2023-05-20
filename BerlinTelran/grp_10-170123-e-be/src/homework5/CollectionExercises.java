package homework5;

import java.util.Map;
import java.util.TreeMap;

/**
 * Java. Homework #5
 * @author Sergey Iryupin
 * @version 19 May 2023
 */
public class CollectionExercises {

    static final String TEXT = "If you really want to learn, and you want " +
            "to learn more quickly and more deeply, pay attention to how you " +
            "pay attention. Think about how you think. Learn how you learn.";

    public static void main(String[] args) {
        // 1st task
        String[] words = TEXT.toLowerCase().split("\\W+");
        Map<String, Integer> wordsMap = processWords(words);
        System.out.println(wordsMap);
        System.out.println("The phrase has " + words.length + " words and " + wordsMap.size() + " non-repeated words.");

        // 2nd task
        PhoneBook pb = new PhoneBook();
        pb.add("John", 918557);
        pb.add("John", 202334);
        System.out.println(pb.get("John"));
    }

    static Map<String, Integer> processWords(String[] words) {
        Map<String, Integer> wm = new TreeMap<>(); // HashMap better for speed
        System.out.println(wm.size());
        for (String word : words) {
            wm.put(word, wm.getOrDefault(word, 0) + 1);
        }
        return wm;
    }
}
