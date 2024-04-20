package pizza.repository;

import java.util.Collection;

public interface CrudRepository<K, V> {
    void put(V value);
    V get(K key);
    void remove(K key);
    Collection<V> values();
}
