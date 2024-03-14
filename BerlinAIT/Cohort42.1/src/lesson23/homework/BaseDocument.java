package lesson23.homework;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseDocument {
    protected int id;
    protected List<ProductAndCount> products;

    public BaseDocument(int id) {
        this.id = id;
        this.products = new ArrayList<>();
    }

    public List<ProductAndCount> getProducts() {
        return products;
    }

    public void add(Product product, int count) {
        products.add(new ProductAndCount(product, count));
    }
}
