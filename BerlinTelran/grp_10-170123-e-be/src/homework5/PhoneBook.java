package homework5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneBook {
    private Map<String, List<Integer>> pb;

    public PhoneBook() {
        pb = new HashMap<>();
    }

    public void add(String name, int phone) {
        //List<Integer> phones = pb.computeIfAbsent(name, k -> new ArrayList<>());
        List<Integer> phones = pb.getOrDefault(name, new ArrayList<>());
        //if (phones == null) {
        //    phones = new ArrayList<>();
        //}
        phones.add(phone);
        pb.put(name, phones);
    }

    public List<Integer> get(String name) {
        return pb.get(name);
    }

    public Map<String, List<Integer>> findAll() {
        return pb;
    }
}
