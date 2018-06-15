/**
 * Class MenuRecord
 * The class describing one menu item
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Oct 10, 2017
 */
class MenuRecord {
    private String name;
    private int weight;
    private int price;

    MenuRecord(String name, int weight, int price) {
        this.name = name;
        this.weight = weight;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "[" + name + ", " + weight + ", " + price + "]";
    }
}