package lesson19;

public class RubberArray {

    private final int INIT_DATA_SIZE = 10;
    private final float RESIZE_KOEF = 1.5f;
    private int[] data;
    private int length;

    public RubberArray() {
        data = new int[INIT_DATA_SIZE];
        length = 0;
    }

    public int get(int idx) {
        return data[idx];
    }

    public void add(int value) {
        if (length == data.length) {
            // create new array, length *= 1.5
            int[] newData = new int[(int) (data.length * RESIZE_KOEF)];
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

    public void add(int value, int idx) {
        // TODO implement
    }

    public void remove(int idx) {
        // move elements right to left from idx
        for (int i = idx; i < data.length - 1; i++) {
            data[i] = data[i + 1];
        }
        length--;
    }

    @Override
    public String toString() {
        // TODO use StringBuilder instead of String
        String str = "[";
        for (int i = 0; i < length; i++) {
            str += String.valueOf(data[i]);
            if (i < length - 1) {
                str += ", ";
            }
        }
        return str + "]";
    }
}
