package pizza.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Pizza order class
 *
 * @author Sergey Iryupin
 * @version 13-May-24
 */
public class Order {
    private Integer id;
    private List<Pizza> orderPizzas; // TODO Pizza -> OrderPizza
    private Customer customer;
    private OrderState state;
    private LocalDateTime createDate;
    private LocalDateTime closeDate;

    public Order() {
    }

    public Order(Customer customer) {
        this.id = null;
        this.orderPizzas = new ArrayList<>();
        this.customer = customer;
        this.state = OrderState.NEW;
        this.createDate = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Pizza> getOrderPizzas() {
        if (orderPizzas == null) {
            orderPizzas = new ArrayList<>();
        }
        return orderPizzas;
    }

    public Customer getCustomer() {
        return customer;
    }

    public OrderState getState() {
        return state;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public void setCloseDate(LocalDateTime closeDate) {
        this.closeDate = closeDate;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ",\n orderPizzas=" + orderPizzas +
                ",\n customer=" + customer +
                ",\n state=" + state +
                ", createDate=" + createDate +
                ", closeDate=" + closeDate +
                '}';
    }
}
