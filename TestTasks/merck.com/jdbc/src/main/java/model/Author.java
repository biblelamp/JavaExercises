package model;

/**
 * Test task Bookshelf from merck.com
 * Class Author provides operation with the relevant table
 *
 * @author Sergey Iryupin
 * @version dated May 28, 2018
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import controller.SQLite;

public class Author implements ITable {
    Connection connection;

    public Author() {
        connection = SQLite.getConnection();
    }

    /**
     * Adding one record into table
     *
     * @param  {int}    id
     * @param  {String} name
     * @return  void
     **/
    public void add(int id, String name) {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS " +
                    getClass().getSimpleName() +
                    "(ID INT PRIMARY KEY NOT NULL," +
                    " NAME TEXT NOT NULL);");
            stmt.executeUpdate(
                    "INSERT INTO " +
                    getClass().getSimpleName() +
                    " (ID, NAME) VALUES (" + id + ", '" + name + "');");
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
        add(Integer.parseInt(fields[0]), fields[1]);
    }

    /**
     * Getting name of author by id
     *
     * @param  {int} id
     * @return {String} name
     **/
    public String get(int id) {
        try (PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM " +
                    getClass().getSimpleName() +
                    " WHERE id=?")) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
                return rs.getString("NAME");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}