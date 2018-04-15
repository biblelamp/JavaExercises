package cz.parser;

/**
 * CLExecutor - the command-line executor
 *
 * @author Sergey Iryupin
 * @version 0.2.1 dated Apr 15, 2018
 */
import java.util.HashMap;
import java.util.Map;

public class CLExecutor {
    private Map<String, Item> map = new HashMap<>();

    public void add(Item item) {
        map.put(item.getName(), item);
    }

    public void execute(String[] args) {
        for(String str : args) {
            System.out.println(str);
        }
    }
}