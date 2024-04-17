package pizza.service;

import pizza.base.ExtСomponent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Extras (additional) components service class
 * Encapsulates the list of extra components & CRUD operations with them
 *
 * @author Sergey Iryupin
 * @version 15-Apr-24
 */
public class ExtComponentService {
    private List<ExtСomponent> components;
    private Map<Integer, ExtСomponent> componentMap;

    public ExtComponentService() {
        components = new ArrayList<>();
        componentMap = new HashMap<>();
    }

    public void init() {
        components.addAll(List.of(
                new ExtСomponent("ham", 25),
                new ExtСomponent("mozzarella", 25),
                new ExtСomponent("champignons", 10),
                new ExtСomponent("pepper salami", 25),
                new ExtСomponent("rukola", 10)
        ));
        componentMap = components.stream().collect(Collectors.toMap(ExtСomponent::getId, с -> с));
    }

    public void add(String name, int price) {
        ExtСomponent component = new ExtСomponent(name, price);
        components.add(component);
        componentMap.put(component.getId(), component);
    }

    public boolean update(int id, String name, int price) {
        ExtСomponent updComponent = componentMap.get(id);
        if (updComponent != null) {
            updComponent.update(name, price);
            return true;
        }
        return false;
    }

    public boolean delete(int id) {
        ExtСomponent delComponent = componentMap.get(id);
        if (delComponent != null) {
            components.remove(delComponent);
            componentMap.remove(id);
            return true;
        }
        return false;
    }

    public void print() {
        components.forEach(System.out::println);
    }
}
