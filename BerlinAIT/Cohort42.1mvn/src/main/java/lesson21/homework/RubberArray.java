package lesson21.homework;

import java.util.Arrays;

public class RubberArray {

    private final int INIT_DATA_SIZE = 10;
    private final float EXTEND_INDEX = 1.5f;
    private int[] data;
    private int length;

    public RubberArray() {
        data = new int[INIT_DATA_SIZE];
        length = 0;
    }

    public int size() {
        return length;
    }

    public int get(int idx) {
        return data[idx];
    }

    private void extendArrayIfNeed() {
        if (length == data.length) {
            // create new array, length * EXTEND_INDEX
            int[] newData = new int[(int) (data.length * EXTEND_INDEX)];
            // move all items to new array
            for (int i = 0; i < data.length; i++) {
                newData[i] = data[i];
            }
            // change link to new array
            data = newData;
        }
    }

    public void add(int value) {
        extendArrayIfNeed();
        // add value
        data[length] = value;
        length++;
    }

    public void add(int value, int idx) {
        if (idx > length) {
            throw new IndexOutOfBoundsException();
        }
        extendArrayIfNeed();
        // insert value
        // 0;1;2;3;4;5
        // 8,4,3,2,1,1 -> i == 5
        // 8,4,3,2,2,1 -> i == 4
        for (int i = length; i > idx; i--) {
            data[i] = data[i - 1];
        }
        data[idx] = value;
        length++;
    }

    public void remove(int idx) {
        if (idx > length) {
            throw new IndexOutOfBoundsException();
        }
        // move elements right to left from idx
        for (int i = idx; i < data.length - 1; i++) {
            data[i] = data[i + 1];
        }
        length--;
    }

    public boolean contains(int value) {
        return indexOf(value) != -1;
    }

    public int indexOf(int value) {
        for (int i = 0; i < length; i++) {
            if (data[i] == value) {
                return i;
            }
        }
        return -1;
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
