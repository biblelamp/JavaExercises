package lesson42;

public class LibraryBook {
    private Integer id;
    private String author;
    private String bookTitle;
    private String genre;
    private String publisher;
    private static int idCounter;

    public LibraryBook(String author, String bookTitle, String genre, String publisher) {
        this.id = ++idCounter;
        this.author = author;
        this.bookTitle = bookTitle;
        this.genre = genre;
        this.publisher = publisher;
    }

    public String getAuthor() {
        return author;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getGenre() {
        return genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + this.author + '\'' +
                ", bookTitle='" + this.bookTitle + '\'' +
                ", genre='" + this.genre + '\'' +
                ", publisher='" + this.publisher + '\'' +
                ", id=" + this.id +
                '}';
    }
}
