package lesson23.homework;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {
    private List<ProductAndCount> products;

    public Warehouse() {
        products = new ArrayList<>();
    }

    public void addAll(List<ProductAndCount> products) {
        // TODO analize and find some problem
        this.products.addAll(products);
    }

    public void deleteAll(List<ProductAndCount> products) {
        for (ProductAndCount pac : products) {
            Product product = pac.getProduct();
            int count = pac.getCount();
            boolean found = false;
            for (ProductAndCount whpac : this.products) {
                if (whpac.getProduct().equals(product)) {
                    // check if we can decrease count of product
                    if (whpac.getCount() >= count) {
                        whpac.setCount(whpac.getCount() - count);
                        found = true;
                        break;
                    } else {
                        // TODO exception: not enought of product
                    }
                }
            }
            if (!found) {
                // TODO exception: product not found
            }
        }
    }

    @Override
    public String toString() {
        return "Warehouse{" + products + '}';
    }
}
