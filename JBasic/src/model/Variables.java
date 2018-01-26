package model;

/**
 * model.Variables - working with variables
 *
 * @author Sergey Iryupin
 * @version 0.1.1 dated Jan 26, 2018
 */
import java.util.HashMap;
import java.util.Map;

public class Variables {
    Map<String, Float> variables;

    public Variables() {
        variables = new HashMap<>();
    }

    public void put(String name, float value) {
        variables.put(name, value);
    }

    public float get(String name) {
        return variables.getOrDefault(name, 0f);
    }

    public boolean isNameValid(String name) {
        return name.matches("^[a-zA-Z][0-9]?");
    }

    public void print() {
        System.out.println(variables);
    }
}