/**
 * Java. Level 2. Lesson 3. Collection Framework
 * Examples for lesson
 *
 * @author Sergey Iryupin
 * @version 0.1 dated 31 Mar 2017
 */
import java.util.*;
 
class Code3Lesson {

    public static void main(String[] args) {
        ArrayListExample();
        HashSetExample();
        TreeSetExample();
        HashMapExample();
        TreeMapExample();
    }

    // https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
    static void ArrayListExample() {
        List<String> al = new ArrayList<>();
        al.add("A");
        al.add("B");
        al.add("C");
        al.add("D");
        al.add("E");
        al.add(1,"А0");
        System.out.println(al) ;
        al.remove("E");
        al.remove(2);
        System.out.println(al);
    }
    
    // https://docs.oracle.com/javase/8/docs/api/java/util/HashSet.html
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

    // https://docs.oracle.com/javase/8/docs/api/java/util/TreeSet.html
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

    // https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html
    static void HashMapExample() {
        Map<String, String> hm = new HashMap<>();
        hm.put("Russia", "Moscow");
        hm.put("France", "Paris");
        hm.put("Germany", "Berlin");
        hm.put("Norway", "Oslo");
        Set<Map.Entry<String, String>> set = hm.entrySet();
        for(Map.Entry<String, String> o : set)
            System.out.println(o.getKey() + ": " + o.getValue());
        System.out.println(hm);
    }

    // https://docs.oracle.com/javase/8/docs/api/java/util/TreeMap.html
    static void TreeMapExample() {
        Map<String, String> tm = new TreeMap<>();
        tm.put("Russia", "Moscow");
        tm.put("France", "Paris");
        tm.put("Germany", "Berlin");
        tm.put("Norway", "Oslo");
        Set<Map.Entry<String, String>> set = tm.entrySet();
        for(Map.Entry<String, String> o : set)
            System.out.println(o.getKey() + ": " + o.getValue());
        System.out.println(tm);
    }
}