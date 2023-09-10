package homework18;

public class MyArrayDataException extends Exception {
    public MyArrayDataException(String data, int row, int column) {
        super("Incorrect number format '" + data + "' in row: " + row + ", column: " + column);
    }
}
