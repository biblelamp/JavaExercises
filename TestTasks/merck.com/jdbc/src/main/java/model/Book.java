package model;

/**
 * Test task Bookshelf from merck.com
 * Class Book provides operation with the relevant table
 *
 * @author Sergey Iryupin
 * @version dated Jun 06, 2018
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import controller.SQLite;

public class Book implements ITable {
    Connection connection;

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
                            getClass().getSimpleName() +
                            "(ID INT PRIMARY KEY NOT NULL," +
                            " NAME TEXT NOT NULL," +
                            " AUTHORID INT NOT NULL);");
            stmt.executeUpdate(
                    "INSERT INTO " +
                            getClass().getSimpleName() +
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

    /**
     * Getting name of author by id of his/her book
     *
     * @param  {int} id
     * @return {String} name of author
     */
    public String getAuthorOfBook(int id) {
        Author author = new Author();
        try (PreparedStatement pstmt = connection.prepareStatement(
                "SELECT * FROM " +
                        getClass().getSimpleName() +
                        " WHERE id=?")) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    return author.get(rs.getInt("AUTHORID"));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Getting a unique list of authors
     *
     * @return {Set<String>} list of authors
     */
    public Set<String> getAuthors() {
        Set<String> list = new HashSet<>();
        Author author = new Author();
        try (ResultSet rs = connection.createStatement()
                .executeQuery(
                     "SELECT * FROM " +
                             getClass().getSimpleName())) {
            while (rs.next()) {
                list.add(author.get(rs.getInt("AUTHORID")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }
}