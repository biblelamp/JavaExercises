package lesson42;

import java.util.*;

public class LibraryBookRepository {
    private Map<Integer, LibraryBook> bookMap;
    public LibraryBookRepository() {
        bookMap = new HashMap<>();
    }
    public Map<Integer, LibraryBook> getBookMap() {
        return bookMap;
    }

    public void put (LibraryBook book) {
        bookMap.put(book.getId(), book);
    }

    public LibraryBook get(Integer catalogNumber) {
        return bookMap.get(catalogNumber);
    }

    public void remove(Integer catalogNumber) {
        bookMap.remove(catalogNumber);
    }

    public Collection<LibraryBook> values() {
        return bookMap.values();
    }

    public void init() {
        List<LibraryBook> books = new ArrayList<>(List.of(
                new LibraryBook("Taras Shevchenko", "Kobzar", "Poetry", "Kyiv Old Guard"),
                new LibraryBook("Ivan Franko", "Za dvoma zaitsiamy", "Drama", "Lviv Printing House"),
                new LibraryBook("Lesia Ukrainka", "Lisova pisnia", "Poetry", "Kyiv Old Guard"),
                new LibraryBook("Ivan Nechuy-Levytsky", "Pіznorid", "Novel", "Kyiv Old Guard"),
                new LibraryBook("Mykhailo Kotsiubynsky", "Intermezzo", "Novel", "Kyiv Old Guard"),
                new LibraryBook("Panas Myrny", "Pisni smutku", "Novel", "Lviv Printing House"),
                new LibraryBook("Ivan Franko", "Pisni smіlі", "Poetry", "Kyiv Old Guard"),
                new LibraryBook("Lesia Ukrainka", "Kaminnyi hospodar", "Drama", "Lviv Printing House"),
                new LibraryBook("Pavlo Tychyna", "Pluh", "Poetry", "Kyiv Old Guard")
        ));
        books.forEach(book -> put(book));
    }
}