package pizza.repository.db;

import pizza.domain.ExtComponent;
import pizza.repository.CrudRepository;

import java.util.Collection;

/**
 * ExtComponent repository
 * Implementation of access methods to the ExtComponent data source
 *
 * @author Sergey Iryupin
 * @version 23-Jun-24
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

    public ExtComponentDbRepository(String dbName) {
        this.dbName = dbName;
    }

    @Override
    public ExtComponent save(ExtComponent value) {
        return null;
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
