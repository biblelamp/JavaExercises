package pizza.service;

import pizza.data.Customer;
import pizza.data.ExtСomponent;
import pizza.data.Order;
import pizza.data.OrderState;
import pizza.data.Pizza;
import pizza.repository.OrderRepository;

/**
 * Order service class
 * Encapsulates the list of orders & CRUD operations with them
 *
 * @author Sergey Iryupin
 * @version 21-Apr-24
 */
public class OrderService {
    private OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public void add(Customer customer) {
        Order order = new Order(customer);
        repository.put(order);
    }

    public Order get(int id) {
        return repository.get(id);
    }

    public boolean delete(int id) {
        Order deleleOrder = repository.get(id);
        if (deleleOrder != null) {
            repository.remove(id);
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

    public void setState(Order order, char charState) {
        OrderState state = null;
        switch (charState) {
            case 'n':
                state = OrderState.NEW;
                break;
            case 'p':
                state = OrderState.PAID;
                break;
            case 'r':
                state = OrderState.RECEIVED;
                break;
            case 'c':
                state = OrderState.CANCELED;
                break;
        }
        if (state != null) {
            order.setState(state);
        }
    }

    public void print() {
        repository.values().forEach(System.out::println);
    }
}
