package model;

/**
 * Test task Bookshelf from merck.com
 * Interface for classes of tables
 *
 * @author Sergey Iryupin
 * @version dated May 29, 2018
 */

import java.sql.Connection;
import java.sql.Statement;

public interface ITable {
    void addLine(String line);
    default ITable dropTable(Connection connection) {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(
                    "DROP TABLE IF EXISTS " + getClass().getSimpleName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return this;
    }
}