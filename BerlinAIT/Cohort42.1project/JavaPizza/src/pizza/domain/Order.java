package pizza.domain;

import java.util.*;

/**
 * Pizza order class
 *
 * @author Sergey Iryupin
 * @version 19-Apr-24
 */
public class Order {
    private int id;
    private Map<Integer, OrderPizza> orderPizzaMap;
    private Customer customer;
    private OrderState state;
    private int deliveryPrice;
    private Date createDate;
    private Date receivedDate;
    private static int idCounter = 0;

    public Order(Customer customer) {
        this.id = ++idCounter;
        this.orderPizzaMap = new HashMap();
        this.customer = customer;
        this.state = OrderState.NEW;
        this.createDate = new Date();
    }

    public int getId() {
        return id;
    }

    public OrderPizza getOrderPizza(int orderPizzaId) {
        return orderPizzaMap.get(orderPizzaId);
    }

    public void addOrderPizza(Pizza pizza) {
        OrderPizza orderPizza = new OrderPizza(pizza);
        orderPizzaMap.put(orderPizza.getId(), orderPizza);
    }

    public void addExtСomponent(int orderPizzaId, ExtСomponent extComponent) {
        OrderPizza orderPizza = orderPizzaMap.get(orderPizzaId);
        orderPizza.addComponent(extComponent);
    }

    public void deleteOrderPizza(int orderPizzaId) {
        orderPizzaMap.remove(orderPizzaId);
    }

    public void deleteExtСomponent(int orderPizzaId, ExtСomponent extComponent) {
        OrderPizza orderPizza = orderPizzaMap.get(orderPizzaId);
        orderPizza.deleteComponent(extComponent);
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
                ",\n orderPizzas=" + orderPizzaMap +
                ",\n customer=" + customer +
                ",\n state=" + state +
                ", deliveryPrice=" + deliveryPrice +
                ", createDate=" + createDate +
                ", receivedDate=" + receivedDate +
                '}';
    }
}
