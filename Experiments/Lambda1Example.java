import java.util.*;
/**
 * Popular about lambda expressions in Java (RU), part 1
 * @see https://javarush.ru/groups/posts/264-populjarno-o-ljambda-vihrazhenijakh-v-java-s-primerami-i-zadachami-chastjh-1-
 */
class Lambda1Example {

    public static void main(String[] args) {
        String[] array1 = {"мама", "мыла", "раму"};
        String[] array2 = {"я", "очень", "люблю", "java"};
        String[] array3 = {"мир", "труд", "май"};

        List<String[]> arrays = new ArrayList<>();
        arrays.add(array1);
        arrays.add(array2);
        arrays.add(array3);

        Comparator<String[]> sortByLength = new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return o1.length - o2.length;
            }
        };

        Comparator<String[]> sortByWordsLength = new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                int length1 = 0;
                int length2 = 0;
                for (String s : o1)
                    length1 += s.length();
                for (String s : o2)
                    length2 += s.length();
                return length1 - length2;
            }
        };

        //arrays.sort(sortByWordsLength);
        //arrays.sort(sortByLength);
        /*
        arrays.sort(new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return o1.length - o2.length;
            }
        });
        */
        arrays.sort((o1, o2) -> o1.length - o2.length);
        arrays.sort((o1, o2) -> {
            int length1 = 0;
            int length2 = 0;
            for (String s : o1)
                length1 += s.length();
            for (String s : o2)
                length2 += s.length();
            return length1 - length2;
        });

        //for (String[] list : arrays)
        //    System.out.println(Arrays.toString(list));
        arrays.forEach(x -> System.out.println(Arrays.toString(x)));
    }
}