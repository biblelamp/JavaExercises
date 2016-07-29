/**
 * Java. Level 2. Lesson 7. Homework
 *  Simple operations with SQLite database
 *  Note:
 *  a) Download latest ver of sqlite-jdbc-(VER).jar from https://bitbucket.org/xerial/sqlite-jdbc/downloads
 *  b) Put this jar into \jre\lib\ext
 *  c) See also http://www.tutorialspoint.com/sqlite/sqlite_java.htm
 *
 * @author Sergey Iryupin
 * @version 29 July 2016
 */
import java.sql.*;

public class SQLite {

    static final String DRIVER_NAME = "org.sqlite.JDBC";
    static Connection connect = null;
    static String nameDB = "sqlite.db";
    static String tableDB = "COMPANY";

	public static void main(String[] args) {
        openDB(nameDB);
        createTable(tableDB);
        insertRecords(tableDB);
        selectRecords(tableDB);

        updateRecord(tableDB);
        selectRecords(tableDB);

        deleteRecord(tableDB);
        selectRecords(tableDB);
        try {
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    static void openDB(String nameDB) {
        try {
            Class.forName(DRIVER_NAME);
            connect = DriverManager.getConnection("jdbc:sqlite:" + nameDB);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
        System.out.println("Opening database " + nameDB + " successfully");
    }
    
    static void createTable(String table) {
        try {
            Statement stmt = connect.createStatement();
            String sql = "CREATE TABLE " + table +
                "(ID INT PRIMARY KEY NOT NULL," +
                " NAME   TEXT    NOT NULL," + 
                " AGE    INT NOT NULL," + 
                " ADDRESS   CHAR(50)," + 
                " SALARY    REAL)";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
        System.out.println("Create table in database " + nameDB + " successfully");
    }

    static void insertRecords(String table) {
        try {
            Statement stmt = connect.createStatement();
            String sql = "INSERT INTO " + table +
                " (ID,NAME,AGE,ADDRESS,SALARY) " +
                "VALUES (1, 'Paul', 32, 'California', 20000.00);";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO " + table +
                " (ID,NAME,AGE,ADDRESS,SALARY) " +
                "VALUES (2, 'Allen', 25, 'Texas', 15000.00);";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO " + table +
                " (ID,NAME,AGE,ADDRESS,SALARY) " +
                "VALUES (3, 'Teddy', 23, 'Norway', 20000.00);";
            stmt.executeUpdate(sql);

            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
        System.out.println("Records in database " + nameDB + " added successfully");
    }

    static void selectRecords(String table) {
        try {
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM " + table + ";" );
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String address = rs.getString("address");
                float salary = rs.getFloat("salary");
                System.out.println("ID = " + id );
                System.out.println("NAME = " + name);
                System.out.println("AGE = " + age);
                System.out.println("ADDRESS = " + address);
                System.out.println("SALARY = " + salary);
                System.out.println();
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    static void updateRecord(String table) {
        try {
            Statement stmt = connect.createStatement();
            String sql = "UPDATE " + table + " set SALARY = 35000.00 where ID=1;";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    static void deleteRecord(String table) {
        try {
            Statement stmt = connect.createStatement();
            String sql = "DELETE from " + table + " where ID=2;";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}