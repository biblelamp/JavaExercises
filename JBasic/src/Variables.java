/**
 * Variables - working with variables
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Jan 25, 2018
 */
import java.util.HashMap;
import java.util.Map;

class Variables {
    Map<String, Float> variables;

    Variables() {
        variables = new HashMap<>();
    }

    void put(String name, String value) {
        try {
            variables.put(name, Float.parseFloat(value));
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }

    float get(String name) {
        try {
            return variables.get(name);
        } catch (NullPointerException ex) {
            return 0;
        }
    }

    void print() {
        System.out.println(variables);
    }
}