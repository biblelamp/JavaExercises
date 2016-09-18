/**
 * Java. Level 3. Lesson 2. Homework
 * 1. Read the file List.csv
 * 2. Parse it and transfer contents to the database (preferably sqlite)
 * 3. Change some of the price / category in a file and to load it again,
 *   track the products that changed their category or price,
 *    when it detects changes to update a record
 *
 * @author Sergey Iryupin
 * @version 18 Sep 2016
 */
import java.sql.*;
import java.io.*;

public class HomeWork2 {

    final String FILE_NAME = "List.csv";
    final String DRIVER_NAME = "org.sqlite.JDBC";
    final String NAME_DB = "store.db";
    final String TABLE_DB = "goods";
    Connection connect = null;
    
    public static void main(String[] args) {
        new HomeWork2().go();
    }
    
    void go() {
        BufferedReader reader = null;
        String[] fields;
        
        // open sqlite database
        open(NAME_DB);
        createTable(NAME_DB, TABLE_DB, "");

        // read and parse the csv file
        try {
            reader = new BufferedReader(
                        new InputStreamReader(
                            new FileInputStream(FILE_NAME), "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                fields = line.split("\t");
                add(fields);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        close();
    }
    
    void open(String nameDB) { // open connection or create DB
        try {
            Class.forName(DRIVER_NAME);
            connect = DriverManager.getConnection("jdbc:sqlite:" + nameDB);
            System.out.println("Opening DB " + nameDB + " successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void createTable(String nameDB, String tableDB, String createTbl) { // create table
        try {
            Statement stmt = connect.createStatement();
            stmt.executeUpdate(createTbl);
            stmt.close();
            System.out.println("Create table " + tableDB + " in DB " + nameDB + " successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    void add(String[] fields) { // add record
    }

    void close() { // close connection
        try {
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}