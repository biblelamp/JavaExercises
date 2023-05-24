package homework6;

public class RubberArray {
    private int[] array;

    public RubberArray() {
        array = new int[0];
    }

    public void addAll(int... values) {
        for (int item : values) {
            add(item);
        }
    }

    public void add(int index, int value) {
        int[] newArray = new int[array.length + 1];
        System.arraycopy(array, 0, newArray, 0, index);
        System.arraycopy(array, index, newArray, index + 1, array.length - index);
        newArray[index] = value;
        array = newArray;
    }

    public void add(int value) {
        int[] newArray = new int[array.length + 1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        newArray[array.length] = value;
        array = newArray;
    }

    public boolean contains(int value) {
        for (int item : array) {
            if (item == value) {
                return true;
            }
        }
        return false;
    }

    public int indexOf(int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return array.length == 0;
    }

    public void clear() {
        int[] newArray = new int[0];
        array = newArray;
    }

    public int size() {
        return array.length;
    }

    public int get(int index) {
        return array[index];
    }

    public void remove(int index) {
        int[] newArray = new int[array.length - 1];
        System.arraycopy(array, 0, newArray, 0, index);
        System.arraycopy(array, index + 1, newArray, index, array.length - index - 1);
        array = newArray;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append(", ");
            }
        }
        return sb.append("]").toString();
    }
}
