package lesson36.homework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneBook {
    private Map<String, List<Integer>> pb = new HashMap<>();

    public void add(String name, int phone) {
        List<Integer> phones = pb.get(name);
        if (phones == null) {
            phones = new ArrayList<>(List.of(phone));
            pb.put(name, phones);
        } else {
            phones.add(phone);
        }
    }

    public List<Integer> get(String name) {
        return pb.get(name);
    }

    @Override
    public String toString() {
        return pb.toString();
    }
}
