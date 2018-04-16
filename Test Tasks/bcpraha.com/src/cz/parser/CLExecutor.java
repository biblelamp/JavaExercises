package cz.parser;

/**
 * CLExecutor - the command-line executor
 *
 * @author Sergey Iryupin
 * @version 0.2.2 dated Apr 16, 2018
 */
import java.util.HashMap;
import java.util.Map;

public class CLExecutor {
    private Map<String, Item> map = new HashMap<>();

    public void add(Item item) {
        map.put(item.getName(), item);
    }

    public void execute(String[] args) {
        for (String str : args) {
            for (Map.Entry<String, Item> item : map.entrySet())
                if (item.getValue().isAlias(str))
                    System.out.println(str);
        }
    }
}