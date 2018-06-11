/**
 * Java. Level 2. Lesson 3. Collection Framework
 * Examples for lesson
 *
 * @author Sergey Iryupin
 * @version dated Jun 11, 2018
 * @see also Wrapper/Decorator (Integer, Long, Float, Double...)
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;

class J2Lesson3 {

    public static void main(String[] args) {
        ArrayListExample();
        HashSetExample();
        TreeSetExample();
        HashMapExample();
        TreeMapExample();
    }

    // @see https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
    static void ArrayListExample() {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        System.out.println(list);
        list.add(1, "A1");
        System.out.println(list);
        list.remove("E");
        System.out.println("We'll remove " + list.get(2));
        list.remove(2);
        System.out.println(list);
    }

    // @see https://docs.oracle.com/javase/8/docs/api/java/util/HashSet.html
    static void HashSetExample() {
        Set<String> hs = new HashSet<>();
        hs.add("Beta");
        hs.add("Alpha");
        hs.add("Eta");
        hs.add("Gamma");
        hs.add("Epsilon");
        hs.add("Omega");
        hs.add("Gamma");
        System.out.println(hs);
    }

    // @see https://docs.oracle.com/javase/8/docs/api/java/util/TreeSet.html
    static void TreeSetExample() {
        Set<String> hs = new TreeSet<>();
        hs.add("Beta");
        hs.add("Alpha");
        hs.add("Eta");
        hs.add("Gamma");
        hs.add("Epsilon");
        hs.add("Omega");
        hs.add("Gamma");
        System.out.println(hs);
    }

    // @see https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html
    static void HashMapExample() {
        Map<String, String> hm = new HashMap<>();
        hm.put("Moscow", "Russia");
        hm.put("Rostov", "Russia");
        hm.put("Paris", "France");
        hm.put("Berlin", "Germany");
        hm.put("Oslo", "Norway");
        Set<Map.Entry<String, String>> set = hm.entrySet();
        for (Map.Entry<String, String> o : set)
            System.out.println(o.getKey() + ": " + o.getValue());
        System.out.println(hm);
        System.out.println(hm.get("Paris"));
    }

    // @see https://docs.oracle.com/javase/8/docs/api/java/util/TreeMap.html
    static void TreeMapExample() {
        Map<String, String> tm = new TreeMap<>();
        tm.put("Moscow", "Russia");
        tm.put("Paris", "France");
        tm.put("Berlin", "Germany");
        tm.put("Oslo", "Norway");
        Set<Map.Entry<String, String>> set = tm.entrySet();
        for(Map.Entry<String, String> o : set)
            System.out.println(o.getKey() + ": " + o.getValue());
        System.out.println(tm);
    }
}