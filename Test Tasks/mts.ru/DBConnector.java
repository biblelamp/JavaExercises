/**
 * Class DBConnector
 * The class simulates the work with the database:
 *  - the product table is simulated by several HashMap
 *  - the table storing requests is emulated by a text file
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Oct 06, 2017
 */
import java.util.*;
import java.io.*;

class DBConnector {
    static Map<String, Integer> prices = new HashMap<String, Integer>();
    static Map<String, Integer> amount = new HashMap<String, Integer>();
    static Map<String, Integer> sales = new HashMap<String, Integer>();
    static Map<String, Integer> limit = new HashMap<String, Integer>();
    static Map<String, Integer> change = new HashMap<String, Integer>();
    static final String LOG_FILE_NAME = "journal.txt";

    static {
        prices.put("apple", 15);    // price
        amount.put("apple", 1000);  // current quantity
        sales.put("apple", 0);      // sold quantity
        limit.put("apple", 100);    // price change boundary
        change.put("apple", -1);    // how the price changes
        prices.put("wine", 250);
        amount.put("wine", 50);
        sales.put("wine", 0);
        limit.put("wine", 20);
        change.put("wine", 2);
    }

    static int getPrice(String name) {
        return prices.get(name);
    }

    static void setPrice(String name, int price) {
        prices.put(name, price);
        writeLog(
            name + "\tset price\t" + Integer.toString(price) + "\tsuccess");
    }

    static void sell(String name, int quantity) {
        Integer balance = amount.get(name);
        if (balance != null && balance >= quantity) {
            amount.put(name, balance - quantity);
            sales.put(name, sales.get(name) + quantity);
            writeLog(
                name + "\tsell\t" + Integer.toString(quantity) + "\tsuccess");
            // change price
            int k = quantity / limit.get(name);
            if (k > 0)
                setPrice(name, getPrice(name) + k * change.get(name));
        } else
            writeLog(
                name + "\tsell\t" + Integer.toString(quantity) + "\tfailed");
    }

    static void add(String name, int quantity) {
        Integer balance = amount.get(name);
        amount.put(name, (balance != null)? balance + quantity : quantity);
        writeLog(
            name + "\tadd\t" + Integer.toString(quantity) + "\tsuccess");
    }

    static void writeLog(String str) {
        try (PrintWriter log = 
            new PrintWriter(
                new FileWriter(LOG_FILE_NAME, true))) {
            log.write((new Date()) + "\t" + str + "\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}