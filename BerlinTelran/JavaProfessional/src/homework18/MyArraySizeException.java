package homework18;

public class MyArraySizeException extends Exception {
    public MyArraySizeException(int errSize, int size) {
        super("Invalid size of matrix: " + errSize + ", must: " + size);
    }
}
