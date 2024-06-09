package pizza.repository.db;

import pizza.domain.Customer;
import pizza.repository.CrudRepository;

import java.util.Collection;

/**
 * Customer repository
 * Implementation of access methods to the Customer data source
 *
 * @author Sergey Iryupin
 * @version 09-Jun-24
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
        return null;
    }

    @Override
    public Customer findById(Integer id) {
        return null;
    }

    @Override
    public Collection<Customer> findAll() {
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
