package lesson23.homework;

public class ToWarehouse extends BaseDocument {

    public ToWarehouse(int id) {
        super(id);
    }

    public void toWarehouse(Warehouse warehouse) {
        warehouse.addAll(products);
    }
}
