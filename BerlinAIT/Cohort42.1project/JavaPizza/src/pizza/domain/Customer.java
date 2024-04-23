package pizza.domain;

/**
 * Pizza customer class
 *
 * @author Sergey Iryupin
 * @version 15-Apr-24
 */
public class Customer {
    private int id;
    private String name;
    private String address;
    private String phone;
    private static int idCounter = 0;

    public Customer(String name, String address, String phone) {
        this.id = ++idCounter;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void update(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "{id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
