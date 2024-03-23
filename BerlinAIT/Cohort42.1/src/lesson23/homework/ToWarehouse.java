package lesson23.homework;

public class ToWarehouse extends BaseDocument {

    public ToWarehouse(int id) {
        super(id);
    }

    public void toWarehouse(Warehouse warehouse, Manager manager) {
        if (this.manager != null) {
            return;
        }
        warehouse.addAll(products);
        this.manager = manager;
    }
}
