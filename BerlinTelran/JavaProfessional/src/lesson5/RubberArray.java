package lesson5;

public class RubberArray {
    private int[] array;

    public int get(int idx) {
        return array[idx];
    }

    public void set(int idx, int item) {
        if (array != null) {
            array[idx] = item;
        }
    }

    public int size() {
        if (array != null) {
            return array.length;
        }
        return 0;
    }

    public void add(int item) {
        if (array == null) {
            array = new int[0];
        }
        int[] newArray = new int[array.length + 1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        newArray[array.length] = item;
        array = newArray;
    }

    public void addAll(int... items) {
        for (int item : items) {
            add(item);
        }
    }

    public boolean remove(Integer item) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == item) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    public void remove(int idx) {
        if (array != null && array.length > 0) {
            int[] newArray = new int[array.length - 1];
            System.arraycopy(array, 0, newArray, 0, idx);
            System.arraycopy(array, idx + 1, newArray, idx, newArray.length - idx);
            array = newArray;
        }
    }

    public boolean contains(int item) {
        for (int i : array) {
            if (i == item) {
                return true;
            }
        }
        return false;
    }

    public int[] toArray() {
        return array;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                sb.append(array[i]);
                if (i != array.length - 1) {
                    sb.append(", ");
                }
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
