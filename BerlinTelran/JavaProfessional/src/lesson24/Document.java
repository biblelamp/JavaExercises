package lesson24;

public class Document {
    private final String name;
    private final int countPage;

    public Document(String name, int pageCount) {
        this.name = name;
        this.countPage = pageCount;
    }

    public String getName() {
        return name;
    }

    public int getCountPage() {
        return countPage;
    }
}
