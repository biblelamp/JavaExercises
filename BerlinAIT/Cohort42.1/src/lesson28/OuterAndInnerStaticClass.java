package lesson28;

public class OuterAndInnerStaticClass {
    private String field = "field";

    private void printText() {
        System.out.println("private method print Text.");
    }

    public void useInnerClass() {
        InnerClass innerClass = new InnerClass();
        System.out.println(innerClass.getField());
        innerClass.innerPrintText();
    }

    static class InnerClass {
        private String getField() {
            //return field;
            return "static class fiedl";
        }

        private void innerPrintText() {
            //printText();
            System.out.println("method of static class");
        }
    }
}
