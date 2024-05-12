package pizza.domain;

import java.util.*;

/**
 * Pizza order class
 *
 * @author Sergey Iryupin
 * @version 12-May-24
 */
public class Order {
    private Integer id;
    private List<OrderPizza> orderPizzas;
    private Customer customer;
    private OrderState state;
    private int deliveryPrice;
    private Date createDate;
    private Date receivedDate;

    public Order(Customer customer) {
        this.id = null;
        this.orderPizzas = new ArrayList<>();
        this.customer = customer;
        this.state = OrderState.NEW;
        this.createDate = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<OrderPizza> getOrderPizzas() {
        return orderPizzas;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public void setDeliveryPrice(int deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ",\n orderPizzas=" + orderPizzas +
                ",\n customer=" + customer +
                ",\n state=" + state +
                ", deliveryPrice=" + deliveryPrice +
                ", createDate=" + createDate +
                ", receivedDate=" + receivedDate +
                '}';
    }
}
