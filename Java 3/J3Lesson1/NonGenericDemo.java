class TestNonGeneric {
    private Object obj;

    public TestNonGeneric(Object obj) {
        this.obj = obj;
    }

    public Object getObj() {
        return obj;
    }

    public String showType() {
        return "Type obj: " + obj.getClass().getName();
    }
}

public class NonGenericDemo {
    public static void main(String args[]) {
        TestNonGeneric str = new TestNonGeneric("Hello");
        System.out.println(str.showType());
        String s = (String) str.getObj();
        System.out.println("value: " + s);
        TestNonGeneric num = new TestNonGeneric(2018);
        System.out.println(num.showType());
        int i = (Integer) num.getObj();
        System.out.println("value: " + i);
        // how to create ClassCastException
        //num = str;
        //Integer v = (Integer) num.getObj();
    }
}