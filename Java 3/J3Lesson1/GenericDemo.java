class TestGeneric<T> {
    private T obj;

    public TestGeneric(T оbj) {
        this.obj = оbj;
    }

    public T getObj() {
        return obj;
    }

    public String showType() {
        return "Type of obj: " + obj.getClass().getName();
    }
}

public class GenericDemo {
    public static void main(String args[]) {
        TestGeneric<String> str = new TestGeneric<>("Hello");
        System.out.println(str.showType());
        String s = str.getObj();
        System.out.println("value: " + s);
        TestGeneric<Integer> num = new TestGeneric<>(2018);
        System.out.println(num.showType());
        int i = num.getObj();
        System.out.println("value: " + i);
        // will be to create ClassCastException?
        //num = str;
        //Integer v = (Integer) num.getObj();
    }
}