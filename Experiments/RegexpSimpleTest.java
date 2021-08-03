class RegexpSimpleTest {
    public static void main(String[] args) {
        String exp = "\\d-\\d";
        String str = "2012-2013";
        System.out.println(str.matches(exp));
    }
}