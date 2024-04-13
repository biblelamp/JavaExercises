package lesson36;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Persons {
    private List<Person> persons;
    Map<Integer, Person> idMap;

    public Persons() {
        persons = new ArrayList<>();
        idMap = new HashMap<>();
    }

    public void add(String name, int age) {
        Person person = new Person(name, age);
        idMap.put(person.getId(), person);
        persons.add(person);
    }

    public boolean delete(int id) {
        Person delPerson = idMap.get(id);
        if (delPerson != null) {
            persons.remove(delPerson);
            idMap.remove(id);
            return true;
        }
        return false;
    }

    public boolean update(int id, String name, int age) {
        Person updPerson = idMap.get(id);
        if (updPerson != null) {
            updPerson.setName(name);
            updPerson.setAge(age);
            return true;
        }
        return false;
    }

    public Person find(int id) {
        return idMap.get(id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        persons.forEach(p -> sb.append(p + "\n"));
        return sb.toString();
    }
}