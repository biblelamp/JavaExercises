import java.util.Map;
import java.util.HashMap;

class HashMapGetNull {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(null, "123");
        String str = map.get(null);
        System.out.println(str);
    }
}
