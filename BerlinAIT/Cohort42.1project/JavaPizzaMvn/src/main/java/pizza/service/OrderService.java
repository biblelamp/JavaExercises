package pizza.service;

import pizza.domain.*;
import pizza.repository.*;

/**
 * Order service class
 * Encapsulates the list of orders & CRUD operations with them
 *
 * @author Sergey Iryupin
 * @version 21-Apr-24
 */
public class OrderService {
    private CrudRepository<Integer, Order> repository;
    private CrudRepository<Integer, Pizza> pizzaRepository;
    private CrudRepository<Integer, OrderPizza> orderPizzaRepozitory;
    private CrudRepository<Integer, ExtComponent> extComponentRepository;

    public OrderService(CrudRepository<Integer, Order> repository,
                        CrudRepository<Integer, Pizza> pizzaRepository,
                        CrudRepository<Integer, OrderPizza> orderPizzaRepozitory,
                        CrudRepository<Integer, ExtComponent> extComponentRepository) {
        this.repository = repository;
        this.pizzaRepository = pizzaRepository;
        this.orderPizzaRepozitory = orderPizzaRepozitory;
        this.extComponentRepository = extComponentRepository;
    }

    public void add(Customer customer) {
        Order order = new Order(customer);
        repository.save(order);
    }

    public Order get(int id) {
        return repository.findById(id);
    }

    public boolean delete(int id) {
        Order deleleOrder = repository.findById(id);
        if (deleleOrder != null) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public void addOrderPizza(int orderId, int pizzaId) {
        Order order = get(orderId);
        Pizza pizza = pizzaRepository.findById(pizzaId);
        OrderPizza orderPizza = new OrderPizza(pizza);
        orderPizzaRepozitory.save(orderPizza);
        order.getOrderPizzas().add(orderPizza);
        repository.save(order);
    }

    public void deleteOrderPizza(int orderId, int orderPizzaId) {
        Order order = get(orderId);
        OrderPizza orderPizza = orderPizzaRepozitory.findById(orderPizzaId);
        order.getOrderPizzas().remove(orderPizza);
        repository.save(order);
        orderPizzaRepozitory.deleteById(orderPizzaId);
    }

    public void addExtСomponent(int orderId, int orderPizzaId, int extСomponentId) {
        Order order = get(orderId);
        OrderPizza orderPizza = orderPizzaRepozitory.findById(orderPizzaId);
        ExtComponent extComponent = extComponentRepository.findById(extСomponentId);
        orderPizza.getComponents().add(extComponent);
        orderPizzaRepozitory.save(orderPizza);
    }

    public void deleteExtСomponent(int orderId, int orderPizzaId, int extСomponentId) {
        Order order = get(orderId);
        OrderPizza orderPizza = orderPizzaRepozitory.findById(orderPizzaId);
        ExtComponent extСomponent = extComponentRepository.findById(extСomponentId);
        orderPizza.getComponents().remove(extСomponent);
        orderPizzaRepozitory.save(orderPizza);
    }

    public void setDeliveryPrice(int orderId, int deliveryPrice) {
        Order order = get(orderId);
        order.setDeliveryPrice(deliveryPrice);
    }

    public void setState(int orderId, char charState) {
        Order order = get(orderId);
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
        repository.findAll().forEach(System.out::println);
    }
}
