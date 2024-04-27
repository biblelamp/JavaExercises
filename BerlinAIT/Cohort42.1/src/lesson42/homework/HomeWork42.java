package lesson42.homework;

import lesson42.LibraryBook;
import lesson42.LibraryBookRepository;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * AIT-TR, cohort 42.1, Java Basic, hw #42
 *
 * @author Sergey Iryupin
 * @version 26-Apr-24
 */
public class HomeWork42 {
    public static void main(String[] args) {
        // task #1
        List<Integer> numbers = List.of(4, 7, 5, 8, 2, 9, 1);
        List<Integer> result = numbers.stream().filter(i -> i % 2 == 0).collect(Collectors.toList());
        System.out.println(result);

        // task #2
        List<String> words = List.of("hEllO", "JaVa", "LAMBDA");
        System.out.println(words.stream()
                .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase())
                .collect(Collectors.toList()));

        // task #3
        LibraryBookRepository libraryBookRepository = new LibraryBookRepository();
        libraryBookRepository.init();
        Collection<LibraryBook> books = libraryBookRepository.values();
        Set<String> authors = books.stream().map(b -> b.getAuthor()).collect(Collectors.toSet());
        for (String author : authors) {
            System.out.println(author);
            System.out.println(books.stream()
                    .filter(b -> b.getAuthor().equals(author))
                    .map(b -> b.getBookTitle())
                    .collect(Collectors.toList()));
        }
        System.out.println(books.stream()
                .map(b -> b.getPublisher())
                .collect(Collectors.toSet()));
    }
}
