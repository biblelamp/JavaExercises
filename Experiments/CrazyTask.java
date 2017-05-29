import java.util.*;
import java.util.function.*;
import java.util.stream.*;

class CrazyTask {

    public static void main(String[] args) {
        final int[] counters = { 0 };
        List<Integer> source = Arrays.asList(0, 0, 1, 0, 1);
        Supplier<Stream<Integer>> bytes = () -> source
            .stream()
            .filter(x -> {
                counters[0]++;
                return x > 0;
            });
        if (bytes.get().findFirst() == bytes.get().reduce((x, y) -> y)) 
            System.out.println(counters[0]--);
        else
            System.out.println(counters[0]++);
    }
}