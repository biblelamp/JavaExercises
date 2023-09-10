package lesson7;

import java.util.Iterator;

public class RubberArray<T> implements Iterable<T> {
    private T[] array;

    public T get(int idx) {
        return array[idx];
    }

    public void add(T item) {
        if (array == null) {
            array = (T[]) new Object[0];
        }
        T[] newArray = (T[]) new Object[array.length + 1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        newArray[array.length] = item;
        array = newArray;
    }

    public void addAll(T... items) {
        for (T item : items) {
            add(item);
        }
    }

    public boolean remove(T item) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(item)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    public void remove(int idx) {
        if (array != null && array.length > 0) {
            T[] newArray = (T[]) new Object[array.length - 1];
            System.arraycopy(array, 0, newArray, 0, idx);
            System.arraycopy(array, idx + 1, newArray, idx, newArray.length - idx);
            array = newArray;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                sb.append(array[i]);
                if (i != array.length - 1) {
                    sb.append(", ");
                }
            }
        }
        return sb.append("]").toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<T> {
        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return array != null && array.length > cursor;
        }

        @Override
        public T next() {
            return array[cursor++];
        }
    }
}
