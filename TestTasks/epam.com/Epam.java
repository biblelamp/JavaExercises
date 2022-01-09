import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

class Epam {
    public static void main(String[] args) {
        List<String> asList = Arrays.asList(new String[] {null, "yuLia", "  ", "borjA", "Ceba", "", "aleNa", "daRek", "FranK", null, "yulia"});
        System.out.println(combine(asList));
    }

    public static String combine(List<String> asList) {
        Set<String> sb = new TreeSet<>();
        for (String str : asList) {
            if (str == null) {
                continue;
            }
            if (str.trim().isEmpty()) {
                continue;
            }
            String w = str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
            sb.add(w);
        }
        return String.join(" - ", sb);
    }
}