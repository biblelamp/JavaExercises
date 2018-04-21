package model;

/**
 * Data - data of currency accounts
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Apr 21, 2018
 */
import java.util.Map;
import java.util.HashMap;

public class Data {
    private Map<String, Double> data;

    public Data() {
        data = new HashMap<>();
    }

    public void addRecord(String record) {
        //
    }

    public void addFromFile(String fileName) {
        //
    }

    @Override
    public String toString() {
        String result = "";
        for (Map.Entry<String, Double> item : data.entrySet())
            result += item.getKey() + " " +  item.getValue() + "\n";
        return result;
    }
}