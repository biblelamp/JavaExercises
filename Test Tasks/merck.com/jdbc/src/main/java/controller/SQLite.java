package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Test task Bookshelf from merck.com
 * Class SQLite provides a connection to the database
 *
 * @author Sergey Iryupin
 * @version dated May 28, 2018
 */

public class SQLite {
    private static Connection connection = null;
    private final static String DRIVER = "org.sqlite.JDBC";
    private final static String PREFIX = "jdbc:sqlite:";
    private final static String DBNAME = "bookshelf.db";

    private SQLite() {} // block of classical object creation

    /**
     * Loads the specified driver
     *
     * @return void
     **/
    private static void loadDriver() {
        try {
            Class.forName(DRIVER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Loads the connection with the database
     *
     * @return void
     **/
    private static void loadConnection() {
        try {
            connection = DriverManager.getConnection(PREFIX + DBNAME);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * To get connection like as the instance for the singleton
     *
     * @return {Connection} connection
     **/
    public static Connection getConnection() {
        if (connection == null) {
            loadDriver();
            loadConnection();
        }
        return connection;
    }
}