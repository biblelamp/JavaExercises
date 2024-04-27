package lesson42;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * AIT-TR, cohort 42.1, Java Basic, Lesson #42
 *
 * @version 24-Apr-24
 */
public class Lesson42 {
    public static void main(String[] args) {
        LibraryBookRepository repository = new LibraryBookRepository();
        repository.init();
        Collection<LibraryBook> books = repository.values();

        // classic
        long count = 0;
        Iterator<LibraryBook> iterator = books.iterator();
        while (iterator.hasNext()) {
            LibraryBook book = iterator.next();
            if (book.getGenre().equals("Poetry")) {
                count++;
            }
        }
        System.out.println(count);

        // using stream
        List<String> list = books.stream()
                .filter(b -> b.getAuthor().equals("Ivan Franko"))
                .map(b -> b.getBookTitle())
                .collect(Collectors.toList());

        list.forEach(System.out::println);
    }
}
