package lesson41.homework.repository;

import lesson41.homework.domain.Book;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BookRepository {
    private Map<Integer, Book> bookMap = new HashMap<>();

    public Book save(Book book) {
        if (book.getId() == null) {
            // if id == null -> add new record with new id
            Set<Integer> ids = bookMap.keySet();
            int bookId = 0;
            for (int id : ids) {
                if (id > bookId) {
                    bookId = id;
                }
            }
            book.setId(bookId + 1);
        }
        bookMap.put(book.getId(), book);
        return book;
    }

    public Book findById(int id) {
        return bookMap.get(id);
    }

    public boolean delete(int id) {
        return bookMap.remove(id) != null;
    }

    public Collection<Book> findAll() {
        return bookMap.values();
    }
}
