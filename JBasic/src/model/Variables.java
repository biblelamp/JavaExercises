package model;

/**
 * model.Variables - working with variables
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Jan 25, 2018
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
        try {
            return variables.get(name);
        } catch (NullPointerException ex) {
            return 0;
        }
    }

    public void print() {
        System.out.println(variables);
    }
}