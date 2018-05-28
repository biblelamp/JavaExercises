package model;

import java.sql.Connection;
import java.sql.Statement;

import controller.SQLite;

/**
 * Test task Bookshelf from merck.com
 * Class Book provides operation with the relevant table
 *
 * @author Sergey Iryupin
 * @version dated May 28, 2018
 */

public class Book {
    Connection connection;
    Statement stmt;

    public Book() {
        connection = SQLite.getConnection();
    }

    /**
     * Adding one record into table
     *
     * @param  {int}    id
     * @param  {String} name
     * @param  {int}    author's id
     * @return  void
     **/
    public void add(int id, String name, int authorID) {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS " +
                            this.getClass().getSimpleName() +
                            "(ID INT PRIMARY KEY NOT NULL," +
                            " NAME TEXT NOT NULL," +
                            " AUTHORID INT NOT NULL);");
            stmt.executeUpdate(
                    "INSERT INTO " +
                            this.getClass().getSimpleName() +
                            " (ID, NAME, AUTHORID) VALUES (" +
                            id + ", '" + name + "', " + authorID + ");");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}