/**
 * Class Product
 * The class providing the basic methods of working with the product
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Oct 06, 2017
 */
class Product implements IProduct {
    private String name;
    private int forSale;

    Product(String name) {
        this.name = name;
    }

    @Override
    public int getPrice() {
        return DBConnector.getPrice(name);
    }

    @Override
    public void sell() {
        DBConnector.sell(name, forSale);
    }

    void setPrice(int price) {
        DBConnector.setPrice(name, price);
    }

    void setForSale(int forSale) {
        this.forSale = forSale;
    }

    void add(int quantity) {
        DBConnector.add(name, quantity);
    }
}