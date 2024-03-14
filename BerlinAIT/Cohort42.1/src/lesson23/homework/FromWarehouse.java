package lesson23.homework;

public class FromWarehouse extends BaseDocument {
    public FromWarehouse(int id) {
        super(id);
    }

    public void add(Order order) {
        products = order.getProducts();
    }

    public void fromWarehouse(Warehouse warehouse) {
        warehouse.deleteAll(products);
    }
}
