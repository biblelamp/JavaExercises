package pizza.repository;

import java.util.Collection;

public interface CrudRepository<K, V> {
    void save(V value);
    V findById(K id);
    Collection<V> findAll();
    void deleteById(K id);
    void deleteAll();
}