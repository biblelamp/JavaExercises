package pizza.repository.db;

import pizza.domain.Customer;
import pizza.repository.CrudRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Customer repository
 * Implementation of access methods to the Customer data source
 *
 * @author Sergey Iryupin
 * @version 16-Jun-24
 */
public class CustomerDbRepository implements CrudRepository<Integer, Customer> {

    private String dbName;

    /*
    CREATE TABLE IF NOT EXISTS customer (
        id          INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
        name       TEXT NOT NULL,
        address    TEXT,
        phone      TEXT
    )
    */

    private final String SQL_INSERT = "INSERT INTO customer (name, address, phone) VALUES (?,?,?)";
    private final String SQL_UPDATE = "UPDATE customer SET name = ?, address = ?, phone = ? WHERE id = ?";
    private final String SQL_FIND_BY_ID = "SELECT * FROM customer WHERE id = ?";
    private final String SQL_DELETE_BY_ID = "DELETE FROM customer WHERE id = ?";
    private final String SQL_FIND_ALL = "SELECT * FROM customer";

    public CustomerDbRepository(String dbName) {
        this.dbName = dbName;
    }

    @Override
    public Customer save(Customer customer) {
        try (Connection connection = DriverManager.getConnection(dbName);
             PreparedStatement psi = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement psu = connection.prepareStatement(SQL_UPDATE)) {
            if (customer.getId() == null) {
                // insert record
                psi.setString(1, customer.getName());
                psi.setString(2, customer.getAddress());
                psi.setString(3, customer.getPhone());
                psi.executeUpdate();

                ResultSet rs = psi.getGeneratedKeys();
                if (rs.next()) {
                    customer.setId(rs.getInt(1));
                }
            } else {
                // update record
                psu.setString(1, customer.getName());
                psu.setString(2, customer.getAddress());
                psu.setString(3, customer.getPhone());
                psu.setInt(4, customer.getId());
                psu.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customer;
    }

    @Override
    public Customer findById(Integer id) {
        Customer customer = null;
        try (Connection connection = DriverManager.getConnection(dbName);
             PreparedStatement ps = connection.prepareStatement(SQL_FIND_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                customer = new Customer(rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("phone"));
                customer.setId(rs.getInt("id"));
            }
            return customer;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Customer> findAll() {
        Collection<Customer> customers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(dbName);
             Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(SQL_FIND_ALL);
            while (rs.next()) {
                Customer customer = new Customer(rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("phone"));
                customer.setId(rs.getInt("id"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }

    @Override
    public void deleteById(Integer id) {
        try (Connection connection = DriverManager.getConnection(dbName);
             PreparedStatement ps = connection.prepareStatement(SQL_DELETE_BY_ID)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAll() {
        throw new NullPointerException("Method not implemented");
    }
}
