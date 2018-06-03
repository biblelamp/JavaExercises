package model;

/**
 * Test task Bookshelf from merck.com
 * Class Reader provides operation with the relevant table
 *
 * @author Sergey Iryupin
 * @version dated May 28, 2018
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import controller.SQLite;

public class Reader implements ITable {
    Connection connection;

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
                            getClass().getSimpleName() +
                            "(ID INT PRIMARY KEY NOT NULL," +
                            " NAME TEXT NOT NULL," +
                            " DATEOFBIRTH DATE," +
                            " BOOKS TEXT);");
            stmt.executeUpdate(
                    "INSERT INTO " +
                            getClass().getSimpleName() +
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

    /**
     * Getting number of readers born in each year
     *
     * @return {Map<Integer, Integer>} botn by years
     */
    public Map<Integer, Integer> getBornByYears() {
        Map<Integer, Integer> map = new HashMap<>();
        try (ResultSet rs = connection.createStatement()
                .executeQuery(
                     "SELECT * FROM " +
                             getClass().getSimpleName())) {
            while (rs.next()) {
                int year = Integer.parseInt(
                        rs.getString("DATEOFBIRTH").substring(0, 4));
                map.put(year, map.getOrDefault(year, 0) + 1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return map;
    }

    /**
     * Getting list of authors with their popularity
     *
     * @return Map<String, Integer> list of authors
     */
    public Map<String, Integer> getMostPopularAuthors() {
        Map<String, Integer> map = new HashMap<>();
        Book book = new Book();
        try (ResultSet rs = connection.createStatement()
                     .executeQuery(
                     "SELECT * FROM " +
                             getClass().getSimpleName())) {
            while (rs.next()) {
                String[] books = rs.getString("BOOKS").split(" ");
                for (String id : books) {
                    String author = book.getAuthorOfBook(Integer.parseInt(id));
                    map.put(author, map.getOrDefault(author, 0) + 1);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return map;
    }
}