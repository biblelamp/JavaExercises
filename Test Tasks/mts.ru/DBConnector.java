/**
 * Class DBConnector
 * The class simulates the work with the database:
 *  - the product table is simulated by several HashMap
 *  - the table storing requests is emulated by a text file
 *
 * @author Sergey Iryupin
 * @version 0.2 dated Oct 07, 2017
 */
import java.util.Date;
import java.util.*;
import java.io.*;
import java.sql.*;

class DBConnector {
    static final String LOG_FILE_NAME = "journal.txt";
    static final String DRIVER_NAME = "org.sqlite.JDBC";
    static final String DB_FILE = "product.db";
    static Connection connect = null;

    public static int getPrice(String name) { // get price by name
        return getIntField(name, "price");
    }

    public static void sell(String name, int quantity) { // sell by name
        Integer price = getPrice(name);
        Integer balance = getAmount(name);
        Integer sales = getSales(name);
        Integer batch = getBatch(name);
        Integer change = getChange(name);
        if (balance != null && balance >= quantity) {
            try (Statement stmt = connect.createStatement()) {
                stmt.executeUpdate(
                    "UPDATE product SET amount=" + (balance - quantity) +
                    " WHERE name='" + name + "';");
                stmt.executeUpdate(
                    "UPDATE product SET sales=" + (sales + quantity) +
                    " WHERE name='" + name + "';");
            } catch (Exception e) {
                e.printStackTrace();
            }
            writeLog(
                name + "\tsell\t" + Integer.toString(quantity) + "\tsuccess");
            // change price
            if (quantity / batch > 0)
                try (Statement stmt = connect.createStatement()) {
                    int newPrice = price + quantity / batch * change;
                    stmt.executeUpdate(
                        "UPDATE product SET price=" + newPrice +
                        " WHERE name='" + name + "';");
                writeLog(
                    name + "\tprice changed\t" + newPrice + "\tsuccess");
                } catch (Exception e) {
                    e.printStackTrace();
                }
        } else
            writeLog(
                name + "\tsell\t" + Integer.toString(quantity) + "\tfailed");
    }

    public static void setPrice(String name, int price) { // set price by name
        openDB();
        try (Statement stmt = connect.createStatement()) {
            stmt.executeUpdate(
                "UPDATE product SET price=" + price + " WHERE name='" + name + "';");
        } catch (Exception e) {
            e.printStackTrace();
        }
        writeLog(
            name + "\tset price\t" + Integer.toString(price) + "\tsuccess");
    }

    private static Integer getAmount(String name) { // get amount by name
        return getIntField(name, "amount");
    }

    private static Integer getSales(String name) { // get sales by name
        return getIntField(name, "sales");
    }

    private static Integer getBatch(String name) { // get batch volume by name
        return getIntField(name, "batch");
    }

    private static Integer getChange(String name) { // get change price by name
        return getIntField(name, "change");
    }

    public static void add(String name, int quantity) { // add quantity by name
        int amount = getAmount(name);
        openDB();
        try (Statement stmt = connect.createStatement()) {
            stmt.executeUpdate(
                "UPDATE product SET amount=" + (amount + quantity) +
                " WHERE name='" + name + "';");
        } catch (Exception e) {
            e.printStackTrace();
        }
        writeLog(
            name + "\tadd\t" + Integer.toString(quantity) + "\tsuccess");
    }

    private static void openDB() { // open and init DB if it's needed
        try {
            // open db
            Class.forName(DRIVER_NAME);
            connect = DriverManager.getConnection("jdbc:sqlite:" + DB_FILE);
            // create tables
            Statement stmt = connect.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS product" +
                "(name   CHAR(12) PRIMARY KEY NOT NULL," +
                " price  INT NOT NULL," +
                " amount INT," +
                " sales  INT," +  // sold quantity
                " batch  INT," +  // batch size for price change
                " change INT);"); // price change
            // add two records if table is empty
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM product;");
            if (rs.getInt(1) == 0) {
                stmt.executeUpdate("INSERT INTO product" +
                    "(name, price, amount, batch, change) " +
                    "VALUES ('apple', 15, 1000, 100, -1);");
                stmt.executeUpdate("INSERT INTO product" +
                    "(name, price, amount, batch, change) " +
                    "VALUES ('wine', 250, 50, 20, 10);");
            }
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Integer getIntField(String name, String field) { // get int field
        Integer result = null;
        openDB();
        try (Statement stmt = connect.createStatement()) {
            ResultSet rs = stmt.executeQuery(
                "SELECT * FROM product WHERE name='" + name + "';");
            while (rs.next())
                result = rs.getInt(field);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static void writeLog(String str) { // write to log file
        try (PrintWriter log = 
            new PrintWriter(
                new FileWriter(LOG_FILE_NAME, true))) {
            log.write((new Date()) + "\t" + str + "\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}