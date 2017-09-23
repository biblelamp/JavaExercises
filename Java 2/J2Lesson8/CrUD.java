/**
 * Java. Level 2. Lesson 8
 * CrUD — Create, Update, Delete
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Sep 23, 2017
 */
import java.util.*;
import java.sql.*;

class CrUD implements IConstants {

    final String TABLE_NAME = "users";
    final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
        "(login  CHAR(6) PRIMARY KEY NOT NULL," +
        " passwd CHAR(6) NOT NULL);";
    final String SQL_SELECT = "SELECT * FROM " + TABLE_NAME + ";";
    final String RECORD_ADDED = "Record added.";
    final String RECORD_DELETED = "Record deleted.";
    final String RECORD_UPDATED = "Record updated.";
    final String UNKNOWN_COMMAND = "Unknown command, use [-a-,-u,-d,-l] only.";
    final String LOGIN_COL = "login";

    Connection connect;
    Statement stmt;
    ResultSet rs;
    String sql;

    public static void main(String[] args) {
        new CrUD(args);
    }

    CrUD(String[] args) {
        if (args.length == 0) {
            Scanner sc = new Scanner(System.in);
            System.out.print("> ");
            String line = sc.nextLine();
            args = line.split(" ");
        }

        if (args[0].equals("-a")) {
            openDBFile(SQLITE_DB);
            createTable(SQL_CREATE_TABLE);
            add(args[1], args[2]);
            System.out.println(RECORD_ADDED);
        } else
            if (args[0].equals("-u")) {
            openDBFile(SQLITE_DB);
            update(args[1], args[2]);
            System.out.println(RECORD_UPDATED);
        } else
            if (args[0].equals("-d")) {
            openDBFile(SQLITE_DB);
            delete(args[1]);
            System.out.println(RECORD_DELETED);
        } else
            if (args[0].equals("-l")) {
            openDBFile(SQLITE_DB);
            list();
        } else
            System.out.println(UNKNOWN_COMMAND);
    }

    void openDBFile(String dbName) { // open database
        try {
            Class.forName(DRIVER_NAME);
            connect = DriverManager.getConnection(dbName);
            stmt = connect.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void createTable(String sqlCreateTable) { // create table
        try {
            stmt.executeUpdate(sqlCreateTable);
        } catch (Exception e) { 
            e.printStackTrace();
        }
    }

    void add(String login, String passwd) { // add record
        try {
            stmt.executeUpdate("INSERT INTO " + TABLE_NAME +
                " (login, passwd) " +
                "VALUES ('" + login + "', '" + passwd + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void update(String login, String passwd) { // update record/passwd by login
        try {
            stmt.executeUpdate("UPDATE " + TABLE_NAME +
                " set PASSWD='" + passwd +
                "' where LOGIN='" + login + "';");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void delete(String login) { // delete record by login
        try {
            stmt.executeUpdate("DELETE from " + TABLE_NAME +
                " where LOGIN='" + login + "';");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void list() { // list all records
        try {
            System.out.println("LOGIN\tPASSWD");
            rs = stmt.executeQuery(SQL_SELECT);
            while (rs.next())
                System.out.println(rs.getString(LOGIN_COL) + "\t\t" +
                    rs.getString(PASSWD_COL));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}