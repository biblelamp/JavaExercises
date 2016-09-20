/**
 * Java. Level 3. Lesson 2. Homework
 * 1. Read the file List.csv
 * 2. Parse it and transfer contents to the database (preferably sqlite)
 * 3. Change some of the price/group in a file and to load it again,
 *    track the products that changed their group or price,
 *    when it detects changes to update a record
 *
 * @author Sergey Iryupin
 * @version 19 Sep 2016
 */
import java.sql.*;
import java.io.*;

public class HomeWork2 {

    final String FILE_NAME = "List.csv";
    final String DRIVER_NAME = "org.sqlite.JDBC";
    final String NAME_DB = "store.db";
    final String TABLE_DB = "GOODS";
    final String CREATE_TBL = 
        "CREATE TABLE if not exists " + TABLE_DB +
        "(ID INTEGER PRIMARY KEY," +
        " GRP1  TEXT," +
        " GRP2  TEXT," +
        " GRP3  TEXT," +
        " GRP4  TEXT," +
        " GRP5  TEXT," +
        " NAME  TEXT NOT NULL," +
        " VCODE TEXT," +
        " FNAME TEXT," +
        " PRICE INTEGER)";
    Connection connect = null;
    Statement stmt = null;
    ResultSet rs = null;

    public static void main(String[] args) {
        new HomeWork2().go();
    }

    void go() {
        BufferedReader reader = null;
        String[] fields;

        // open sqlite database
        open(NAME_DB);
        createTable(NAME_DB, TABLE_DB, CREATE_TBL);

        // read and parse the csv file
        try {
            reader = new BufferedReader(
                        new InputStreamReader(
                            new FileInputStream(FILE_NAME), "UTF-8"));
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                fields = line.split("\t");
                try {
                    rs = stmt.executeQuery("SELECT COUNT() FROM " + TABLE_DB + " WHERE ID=" + fields[6] + ";" );
                    if (rs.getInt("count()") == 0) {
                        add(NAME_DB, TABLE_DB, fields);
                    } else {
                        rs = stmt.executeQuery("SELECT GRP1,GRP2,GRP3,GRP4,GRP5,PRICE FROM " + TABLE_DB + " WHERE ID=" + fields[6] + ";" );
                        if (!fields[0].equals(rs.getString("GRP1")) || 
                            !fields[1].equals(rs.getString("GRP2")) ||
                            !fields[2].equals(rs.getString("GRP3")) ||
                            !fields[3].equals(rs.getString("GRP4")) ||
                            !fields[4].equals(rs.getString("GRP5")) ||
                            !fields[9].equals(rs.getString("PRICE"))) {
                                update(NAME_DB, TABLE_DB, fields);
                            }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
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
            stmt = connect.createStatement();
            stmt.executeUpdate(createTbl);
            stmt.close();
            System.out.println("Table " + tableDB + " in DB " + nameDB + " created successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void add(String nameDB, String tableDB, String[] fields) { // add record
        try {
            stmt = connect.createStatement();
            String sql =
                "INSERT INTO " + tableDB +
                " (GRP1,GRP2,GRP3,GRP4,GRP5,NAME,ID,VCODE,FNAME,PRICE)" +
                " VALUES ('" + 
                fields[0] + "', '" + // GRP1
                fields[1] + "', '" + // GRP2
                fields[2] + "', '" + // GRP3
                fields[3] + "', '" + // GRP4
                fields[4] + "', '" + // GRP5
                fields[5] + "', " +  // NAME
                fields[6] + ", '" +  // ID
                fields[7] + "', '" + // VCODE
                fields[8] + "', " +  // FNAME
                fields[9] + ");";    // PRICE
            stmt.executeUpdate(sql);
            stmt.close();
            System.out.println("Record added to the table " + tableDB + " in DB " + nameDB + " successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void update(String nameDB, String tableDB, String[] fields) { // update record
        try {
            stmt = connect.createStatement();
            String sql = "UPDATE " + tableDB + " set " +
            "GRP1='" + fields[0] + "'," +
            "GRP2='" + fields[1] + "'," +
            "GRP3='" + fields[2] + "'," +
            "GRP4='" + fields[3] + "'," +
            "GRP5='" + fields[4] + "'," +
            "PRICE=" + fields[9] + " where ID='" + fields[6] + "';";
            stmt.executeUpdate(sql);
            stmt.close();
            System.out.println("Record updated in the table " + tableDB + " in DB " + nameDB + " successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void close() { // close connection
        try {
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}