/**
 * Java. Level 2. Lesson 3. Example of homework
 *
 * @author Sergey Iryupin
 * @version 0.1 dated 04 Apr 2017
 */
import java.util.*;

public class HW3Lesson {

    public static void main(String[] args) {
        сountWords();
    }

    static void сountWords() {
        int value;
        TreeMap<String, Integer> hm = new TreeMap<>();
        String text = "If you really want to learn, and you want to learn more quickly and more deeply, pay attention to how you pay attention. Think about how you think. Learn how you learn.";
        String[] words = text.toLowerCase().split("[ {,|.}?]+");
        for (String word : words) {
            try {
                value = hm.get(word);
            } catch(NullPointerException e) { 
                value = 0;
            }
            hm.put(word, value + 1);
        }
        System.out.println(hm);
    }
}