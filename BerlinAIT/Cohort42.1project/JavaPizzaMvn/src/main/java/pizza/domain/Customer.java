package pizza.domain;

/**
 * Pizza customer class
 *
 * @author Sergey Iryupin
 * @version 13-May-24
 */
public class Customer {
    private Integer id;
    private String name;
    private String address;
    private String phone;

    public Customer(String name, String address, String phone) {
        this.id = null;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
