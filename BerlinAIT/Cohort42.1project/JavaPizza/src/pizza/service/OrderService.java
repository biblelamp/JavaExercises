package pizza.service;

import pizza.data.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Order service class
 * Encapsulates the list of orders & CRUD operations with them
 *
 * @author Sergey Iryupin
 * @version 19-Apr-24
 */
public class OrderService {
    private Map<Integer, Order> orderMap;

    public OrderService() {
        orderMap = new HashMap<>();
    }

    public void add(Customer customer) {
        Order order = new Order(customer);
        orderMap.put(order.getId(), order);
    }

    public Order get(int id) {
        return orderMap.get(id);
    }

    public boolean delete(int id) {
        Order deleleOrder = orderMap.get(id);
        if (deleleOrder != null) {
            orderMap.remove(deleleOrder);
            return true;
        }
        return false;
    }

    public void addOrderPizza(Order order, Pizza pizza) {
        order.addOrderPizza(pizza);
    }

    public void deleteOrderPizza(Order order, int orderPizzaId) {
        order.deleteOrderPizza(orderPizzaId);
    }

    public void addExtСomponent(Order order, int orderPizzaId, ExtСomponent extСomponent) {
        order.addExtСomponent(orderPizzaId, extСomponent);
    }

    public void deleteExtСomponent(Order order, int orderPizzaId, ExtСomponent extСomponent) {
        order.deleteExtСomponent(orderPizzaId, extСomponent);
    }

    public void setDeliveryPrice(Order order, int deliveryPrice) {
        order.setDeliveryPrice(deliveryPrice);
    }

    public void setState(Order order, OrderState state) {
        order.setState(state);
    }

    public void print() {
        orderMap.values().forEach(System.out::println);
    }
}
