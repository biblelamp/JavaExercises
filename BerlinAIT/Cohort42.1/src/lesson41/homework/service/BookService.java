package lesson41.homework.service;

import lesson41.homework.domain.Book;
import lesson41.homework.repository.BookRepository;

public class BookService {
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book add(String name) {
        Book book = new Book(null, name);
        return bookRepository.save(book);
    }

    public boolean update(int id, String name) {
        Book book = bookRepository.findById(id);
        if (book != null) {
            book.setName(name);
            bookRepository.save(book);
            return true;
        }
        return false;
    }

    public boolean delete(int id) {
        return bookRepository.delete(id);
    }

    public void print() {
        bookRepository.findAll().forEach(System.out::println);
    }
}
