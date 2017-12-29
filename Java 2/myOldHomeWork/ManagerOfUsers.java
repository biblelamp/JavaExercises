/**
 * Java. Level 2. Lesson 7.1 Homework
 * Create a simple console manager of chat's users
 *
 * @author Sergey Iryupin
 * @version 31 July 2016
 */
import java.sql.*;
import java.util.*;

public class ManagerOfUsers {

    final String DRIVER_NAME = "org.sqlite.JDBC";
    final String nameDB = "chat_users.db";
    final String tableDB = "users";
    final String createTbl = "CREATE TABLE " + tableDB +
        "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
        " NAME   TEXT NOT NULL," +
        " PASSWD TEXT)";
    final String select = "SELECT * FROM " + tableDB + ";";
    Connection connect = null;
    Scanner read = new Scanner(System.in);
    String command = "";
    final String CMD_CREATE = "create";
    final String CMD_LIST = "list";
    final String CMD_ADD = "add";
    final String CMD_UPD = "update";
    final String CMD_DEL = "del";
    final String CMD_EXIT = "exit";

    public static void main(String[] args) {
        new ManagerOfUsers().go();
    }

    void go() {
        while (true) {
            System.out.print("Command: ");
            command = read.next();
            if (command.equalsIgnoreCase(CMD_CREATE)) {
                open();
                createTable();
                close();
            } else if (command.equalsIgnoreCase(CMD_LIST)) {
                open();
                list();
                close();
            } else if (command.equalsIgnoreCase(CMD_ADD)) {
                open();
                add();
                close();
            } else if (command.equalsIgnoreCase(CMD_DEL)) {
                open();
                delete();
                close();
            } else if (command.equalsIgnoreCase(CMD_UPD)) {
                open();
                update();
                close();
            } else if (command.equalsIgnoreCase(CMD_EXIT)) {
                break;
            }
        
        }
    }

    void open() { // open connection or create DB
        try {
            Class.forName(DRIVER_NAME);
            connect = DriverManager.getConnection("jdbc:sqlite:" + nameDB);
            System.out.println("Opening DB " + nameDB + " successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void createTable() { // create table
        try {
            Statement stmt = connect.createStatement();
            stmt.executeUpdate(createTbl);
            stmt.close();
            System.out.println("Create table " + tableDB + " in DB " + nameDB + " successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void list() { // list of records
        try {
            System.out.println("#ID\t#NAME\t#PASSWD");
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(select);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String passwd = rs.getString("passwd");
                System.out.println(id + "\t" + name + "\t******");
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void add() {
        System.out.print(">" + CMD_ADD + ">name: ");
        String name = read.next();
        System.out.print(">" + CMD_ADD + ">passwd: ");
        String passwd = read.next();
        try {
            Statement stmt = connect.createStatement();
            String sql = "INSERT INTO " + tableDB +
                " (NAME,PASSWD) VALUES ('" + name + "', '" + passwd + "');";
            stmt.executeUpdate(sql);
            stmt.close();
            System.out.println("Add record in table " + tableDB + " in DB " + nameDB + " successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void delete() {
        System.out.print(">" + CMD_DEL + ">name: ");
        String name = read.next();
        try {
            Statement stmt = connect.createStatement();
            String sql = "DELETE from " + tableDB + " where NAME='" + name + "';";
            stmt.executeUpdate(sql);
            stmt.close();
            System.out.println("Delete record in table " + tableDB + " in DB " + nameDB + " successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void update() {
        System.out.print(">" + CMD_DEL + ">name: ");
        String name = read.next();
        System.out.print(">" + CMD_DEL + ">new name: ");
        String newName = read.next();
        try {
            Statement stmt = connect.createStatement();
            String sql = "UPDATE " + tableDB + " set NAME='" + newName + "' where NAME='" + name + "';";
            stmt.executeUpdate(sql);
            stmt.close();
            System.out.println("Update record in table " + tableDB + " in DB " + nameDB + " successfully");
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