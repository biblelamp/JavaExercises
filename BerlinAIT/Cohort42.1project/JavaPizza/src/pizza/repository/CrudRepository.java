package pizza.repository;

import java.util.Collection;

public interface CrudRepository<K, V> {
    Collection<V> findAll();
    V findById(K key);
    V save(V value);
    void remove(K key);
}
