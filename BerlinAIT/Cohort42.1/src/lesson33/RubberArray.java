package lesson33;

import java.util.Iterator;

public class RubberArray<E> implements Iterable<E> {

    private final int INIT_DATA_SIZE = 10;
    private final float EXTEND_INDEX = 1.5f;
    private Object[] data;
    private int length;

    public RubberArray() {
        data = new Object[INIT_DATA_SIZE];
        length = 0;
    }

    public E get(int idx) {
        return (E) data[idx];
    }

    public void add(E value) {
        if (length == data.length) {
            // create new array, length *= 1.5
            Object[] newData = new Object[(int)(data.length * EXTEND_INDEX)];
            // move all items to new array
            for (int i = 0; i < data.length; i++) {
                newData[i] = data[i];
            }
            // change link to new array
            data = newData;
        }
        // add value
        data[length] = value;
        length++;
    }

    public void add(E value, int idx) {
        // TODO implement
    }

    public void remove(int idx) {
        // move elements right to left from idx
        for (int i = idx; i < data.length - 1; i++) {
            data[i] = data[i + 1];
        }
        length--;
    }

    public boolean contains(E value) {
        return indexOf(value) != -1;
    }

    public int indexOf(E value) {
        for (int i = 0; i < length; i++) {
            if (data[i] == value) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {
        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return length > cursor;
        }

        @Override
        public E next() {
            return (E) data[cursor++];
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < length; i++) {
            sb.append(data[i]);
            if (i < length - 1) {
                sb.append(", ");
            }
        }
        return sb.append("]").toString();
    }
}
