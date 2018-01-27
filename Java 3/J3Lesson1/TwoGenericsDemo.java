class TwoGenerics<T, V> {
    private T obj1;
    private V obj2;

    public TwoGenerics(T obj1, V obj2) {
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    public T getObj1() {
        return obj1;
    }

    public V getObj2() {
        return obj2;
    }

    public String showType() {
        return "Type T: " + obj1.getClass().getName() + "\n" +
            "Type V: " + obj2.getClass().getName();
    }
}

public class TwoGenericsDemo {
    public static void main(String args[]) {
        TwoGenerics<Integer, String> w = new TwoGenerics<>(2018, "Hello");
        System.out.println(w.showType());
        int i = w.getObj1();
        String str = w.getObj2();
        System.out.println("value 1: " + i);
        System.out.println("value 2: " + str);
    }
}