package model;

/**
 * Test task Bookshelf from merck.com
 * Class Book provides operation with the relevant table
 *
 * @author Sergey Iryupin
 * @version dated May 28, 2018
 */

import java.sql.Connection;
import java.sql.Statement;

import controller.SQLite;

public class Book implements ITable {
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

    /**
     * Adding one record from SCV line
     *
     * @param  {String} line
     * @return  void
     **/
    public void addLine(String line) {
        String[] fields = line.split(",");
        add(Integer.parseInt(fields[0]), fields[1], Integer.parseInt(fields[2]));
    }
}