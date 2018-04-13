package cz.parser;

/**
 * CLExecutor - the command-line executor
 *
 * @author Sergey Iryupin
 * @version 0.2 dated Apr 13, 2018
 */
import java.util.Map;

public class CLExecutor {
    private Map<String, Item> map;

    public void add(Item item) {
        map.put(item.getName(), item);
    }

    public void execute(String[] args) {
    }
}