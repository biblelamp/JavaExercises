package lesson7;

public class SimpleBox<V> {
    private V value;

    public SimpleBox(V value) {
        this.value = value;
    }

    public V getValue() {
        return value;
    }
}
