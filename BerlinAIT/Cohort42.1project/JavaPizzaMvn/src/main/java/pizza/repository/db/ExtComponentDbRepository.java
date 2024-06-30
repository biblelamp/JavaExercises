package pizza.repository.db;

import pizza.domain.ExtComponent;
import pizza.repository.CrudRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

/**
 * ExtComponent repository
 * Implementation of access methods to the ExtComponent data source
 *
 * @author Sergey Iryupin
 * @version 30-Jun-24
 */
public class ExtComponentDbRepository implements CrudRepository<Integer, ExtComponent> {

    private String dbName;

    /*
        CREATE TABLE IF NOT EXISTS ext_component (
            id      INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
            name    TEXT NOT NULL,
            price   INTEGER
        )
    */

    private final String SQL_INSERT = "INSERT INTO ext_component (name, price) VALUES (?,?)";
    private final String SQL_UPDATE = "UPDATE ext_component SET name = ?, price = ? WHERE id = ?";
    private final String SQL_FIND_BY_ID = "SELECT * FROM ext_component WHERE id = ?";
    private final String SQL_DELETE_BY_ID = "DELETE FROM ext_component WHERE id = ?";
    private final String SQL_FIND_ALL = "SELECT * FROM ext_component";

    public ExtComponentDbRepository(String dbName) {
        this.dbName = dbName;
    }

    @Override
    public ExtComponent save(ExtComponent component) {
        try (Connection connection = DriverManager.getConnection(dbName);
             PreparedStatement psi = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement psu = connection.prepareStatement(SQL_UPDATE)) {
            if (component.getId() == null) {
                // insert record
                psi.setString(1, component.getName());
                psi.setInt(2, component.getPrice());
                psi.executeUpdate();

                ResultSet rs = psi.getGeneratedKeys();
                if (rs.next()) {
                    component.setId(rs.getInt(1));
                }
            } else {
                // update record
                psi.setString(1, component.getName());
                psi.setInt(2, component.getPrice());
                psu.setInt(3, component.getId());
                psu.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return component;
    }

    @Override
    public ExtComponent findById(Integer id) {
        return null;
    }

    @Override
    public Collection<ExtComponent> findAll() {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void deleteAll() {
        throw new NullPointerException("Method not implemented");
    }
}
