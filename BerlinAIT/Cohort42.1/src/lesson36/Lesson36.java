package lesson36;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * AIT-TR, cohort 42.1, Java Basic, Lesson #36
 *
 * @version 10-Apr-24
 */
public class Lesson36 {
    public static void main(String[] args) {
        String text = "To be or not to be that is the question " +
                "Whether it is nobler in the mind to suffer " +
                "The slings and arrows of outrageous fortune " +
                "Or to take arms against a sea of troubles " +
                "And by opposing end them To die to sleep " +
                "No more and by a sleep to say we end";
        String[] words = text.toLowerCase().split(" ");
        Map<String, Integer> wordsMap = new TreeMap<>();
        for (String word : words) {
            Integer value = wordsMap.get(word);
            if (value == null) {
                wordsMap.put(word, 1);
            } else {
                wordsMap.put(word, value + 1);
            }
        }
        System.out.println(wordsMap);
    }
}
