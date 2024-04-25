package lesson41.homework.repository;

import lesson41.homework.domain.Book;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BookRepository {
    private Map<Integer, Book> bookMap = new HashMap<>();

    public Book findById(int id) {
        return bookMap.get(id);
    }

    public void save(Book book) {
        bookMap.put(book.getId(), book);
    }

    public boolean delete(int id) {
        return bookMap.remove(id) != null;
    }

    public Collection<Book> findAll() {
        return bookMap.values();
    }
}
