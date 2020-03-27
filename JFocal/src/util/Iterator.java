package util;

import java.util.Arrays;
import java.util.Set;

public class Iterator<E> {

    private E[] items;

    private int index;

    public Iterator(Set<E> set) {
        items = (E[]) set.toArray();
        index = -1;
    }

    public boolean hasNext() {
        return index < items.length - 1;
    }

    public E next() {
        if (hasNext()) {
            return items[++index];
        }
        return null;
    }

    public boolean set(E item) {
        int idx = Arrays.binarySearch(items, item);
        if (idx > -1) {
            index = idx;
            return true;
        }
        return false;
    }

}
