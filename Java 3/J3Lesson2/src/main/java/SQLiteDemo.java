/**
 * Java. Level 3. Lesson 2. JDBC example
 *
 *  Note:
 *  a) Download latest ver of sqlite-jdbc-(VER).jar
 *      from https://bitbucket.org/xerial/sqlite-jdbc/downloads
 *  b) Put this jar into [JDK]\jre\lib\ext
 *  or (in IDEA) <ctrl>+<alt>+<shift>+s -> Libraries -> +
 *  or use Maven (pom.xml)
 *
 * @author Sergey Iryupin
 * @version Jul 08, 2018
 */
import java.sql.*;

public class SQLiteDemo {
    static Connection connection = null;
    final String DRIVER_NAME = "org.sqlite.JDBC";
    final String DB_NAME = "jdbc:sqlite:sqlite.db";
    final String tableDB = "books";

    public static void main(String[] args) {
        new SQLiteDemo();
    }

    SQLiteDemo() {
        getConnection();
        closeConnection();
    }

    Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName(DRIVER_NAME);
                connection = DriverManager.getConnection(DB_NAME);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}