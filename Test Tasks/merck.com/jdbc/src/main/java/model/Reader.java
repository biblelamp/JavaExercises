package model;

/**
 * Test task Bookshelf from merck.com
 * Class Reader provides operation with the relevant table
 *
 * @author Sergey Iryupin
 * @version dated May 28, 2018
 */

import java.sql.Connection;
import java.sql.Statement;

import controller.SQLite;

public class Reader implements ITable {
    Connection connection;
    Statement stmt;

    public Reader() {
        connection = SQLite.getConnection();
    }

    /**
     * Adding one record into table
     *
     * @param  {int}    id
     * @param  {String} name
     * @param  {String} day of birth
     * @param  {String} list of books
     * @return  void
     **/
    public void add(int id, String name, String dateOfBirth, String books) {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS " +
                            this.getClass().getSimpleName() +
                            "(ID INT PRIMARY KEY NOT NULL," +
                            " NAME TEXT NOT NULL," +
                            " DATEOFBIRTH DATE," +
                            " BOOKS TEXT);");
            stmt.executeUpdate(
                    "INSERT INTO " +
                            this.getClass().getSimpleName() +
                            " (ID, NAME, DATEOFBIRTH, BOOKS) VALUES (" +
                            id + ", '" + name + "', '" + dateOfBirth + "', '" +
                            books + "');");
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
        add(Integer.parseInt(fields[0]), fields[1], fields[2], fields[3]);
    }
}