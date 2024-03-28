package lesson32;

public class GenericBox<T> {
    private T obj;

    public GenericBox(T obj) {
        this.obj = obj;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}
