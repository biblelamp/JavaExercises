class RegexpSimpleTest {
    public static void main(String[] args) {
        String exp = "\\d+-\\d+";
        String str = "2013-2014";
        System.out.println(str.matches(exp));

        String pattern = "^(https?|elza-node)://.*";
        String url = "https://bugzilla.lightcomp.cz/show_bug.cgi?id=8597";
        System.out.println(url.matches(pattern));
    }
}