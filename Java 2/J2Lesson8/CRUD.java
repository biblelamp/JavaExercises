/**
 * Java. Level 2. Lesson 8
 * CRUD — Create, Read, Update, Delete
 *
 * @author Sergey Iryupin
 * @version 0.2 dated Jan 19, 2018
 */
import java.util.*;
import java.sql.*;

class CRUD implements IConstants {

    final String NAME_TABLE = "users";
    final String SQL_CREATE_TABLE =
        "DROP TABLE IF EXISTS " + NAME_TABLE + ";" +
        "CREATE TABLE " + NAME_TABLE +
        "(login  CHAR(6) PRIMARY KEY NOT NULL," +
        " passwd CHAR(6) NOT NULL);";
    final String SQL_SELECT = "SELECT * FROM " + NAME_TABLE + ";";
    final String DB_CREATED = "Darabase created.";
    final String RECORD_ADDED = "Record added.";
    final String RECORD_DELETED = "Record deleted.";
    final String RECORD_UPDATED = "Record updated.";
    final String UNKNOWN_COMMAND = "Unknown command, use [-c,-a,-r,-u,-d] only.";
    final String LOGIN_COL = "login";

    Connection connect;
    Statement stmt;
    ResultSet rs;
    String sql;

    public static void main(String[] args) {
        new CRUD(args);
    }

    CRUD(String[] args) {
        if (args.length == 0) {
            Scanner sc = new Scanner(System.in);
            System.out.print("> ");
            String line = sc.nextLine();
            args = line.split(" ");
        }

        switch (args[0]) {
            case "-c": 
                openDBFile(SQLITE_DB)
                    .createTable(SQL_CREATE_TABLE);
                System.out.println(DB_CREATED);
                break;
            case "-a": 
                openDBFile(SQLITE_DB)
                    .add(args[1], args[2]);
                System.out.println(RECORD_ADDED);
                break;
            case "-r": 
                openDBFile(SQLITE_DB)
                    .list();
                break;
            case "-u": 
                openDBFile(SQLITE_DB)
                    .update(args[1], args[2]);
                System.out.println(RECORD_UPDATED);
                break;
            case "-d": 
                openDBFile(SQLITE_DB)
                    .delete(args[1]);
                System.out.println(RECORD_DELETED);
                break;
            default:
                System.out.println(UNKNOWN_COMMAND);
        }
    }

    private CRUD openDBFile(String dbName) { // open/create database
        try {
            Class.forName(DRIVER_NAME);
            connect = DriverManager.getConnection(dbName);
            stmt = connect.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    private void createTable(String sqlCreateTable) { // create table
        try {
            stmt.executeUpdate(sqlCreateTable);
        } catch (Exception e) { 
            e.printStackTrace();
        }
    }

    private void add(String login, String passwd) { // add record
        try {
            stmt.executeUpdate("INSERT INTO " + NAME_TABLE +
                " (login, passwd) " +
                "VALUES ('" + login + "', '" + passwd + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void update(String login, String passwd) { // update passwd by login
        try {
            stmt.executeUpdate("UPDATE " + NAME_TABLE +
                " set PASSWD='" + passwd +
                "' where LOGIN='" + login + "';");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void delete(String login) { // delete record by login
        try {
            stmt.executeUpdate("DELETE from " + NAME_TABLE +
                " where LOGIN='" + login + "';");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void list() { // show all records
        try {
            System.out.println("LOGIN\t\tPASSWD");
            rs = stmt.executeQuery(SQL_SELECT);
            while (rs.next())
                System.out.println(
                    rs.getString(LOGIN_COL) + "\t\t" +
                    rs.getString(PASSWD_COL));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
