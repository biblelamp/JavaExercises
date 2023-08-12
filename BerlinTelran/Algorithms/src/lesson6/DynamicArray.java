package lesson6;

public class DynamicArray {
    private int[] array;
    private int size;

    public DynamicArray() {
        array = new int[10];
        size = 0;
    }

    public DynamicArray(int capacita) {
        array = new int[capacita];
        size = 0;
    }

    public int size() {
        return size;
    }

    public int get(int idx) {
        return array[idx];
    }

    public void add(int value) {
        expandArrayIfNeed();
        array[size] = value;
        size++;
    }

    public void add(int idx, int value) {
        expandArrayIfNeed();
        System.arraycopy(array, idx, array, idx + 1, array.length - idx - 1);
        array[idx] = value;
        size++;
    }

    private void expandArrayIfNeed() {
        if (size == array.length) {
            int[] extArray = new int[array.length * 2];
            System.arraycopy(array, 0, extArray, 0, array.length);
            array = extArray;
            //System.out.println("Array size *2 = " + array.length);
        }
    }

    public void remove(int idx) {
        // TODO check size array
        System.arraycopy(array, idx + 1, array, idx, array.length - idx - 1);
        size--;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("[");
        for (int i = 0; i < size; i++) {
            sb.append(array[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        return sb.append("]").toString();
    }
}
