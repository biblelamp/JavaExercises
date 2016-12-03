/*
 * Implementation of queue using ArrayList with generic
 * Access type: LIFO (Last In First Out)
 */

import java.util.*;

class DimamicQueue<T> {
    private List<T> queue = new ArrayList<T>();

    void add(T item) {
        queue.add(item);
    }

    T extract() {
        T result = queue.get(0);
        queue.remove(0);
        return result;
    }

    boolean isEmpty() {
        return (queue.size() == 0);
    }
    
    public String toString() {
        return queue.toString();
    }
}