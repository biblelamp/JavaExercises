package lesson24;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * AIT-TR, cohort 42.1, Java Basic, Lesson #24
 *
 * @version 7-Mar-24
 */
public class Lesson24 {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:C:/temp/sqlite_db");
        Statement stmt = connection.createStatement();

        //add("Muzafar", "cohort41", stmt);
        //read(stmt);

        // CRUD - create, read, update, delete

        stmt.close();
        connection.close();
    }

    static void add(String name, String groupName, Statement stmt) throws SQLException {
        stmt.executeUpdate("INSERT INTO students (name, group_name) VALUES ('" + name + "', '" + groupName + "')");
    }

    static void read(Statement stmt) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT * FROM students;");
        while (rs.next()) {
            System.out.println(
                    rs.getInt("id") + ", " +
                    rs.getString("name") + ", " + rs.getString("group_name"));
        }
    }

    static void update(String name, String group_name, Statement stmt) {
        // TODO implement update group_name by name
    }

    static void delete(int id, Statement stmt) {
        // TODO implemen delete by id
    }
}
