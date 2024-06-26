package pizza.service;

import pizza.domain.*;
import pizza.repository.*;
import pizza.repository.db.OrderDbRepository;
import pizza.repository.db.OrderPizzaDbRepository;

/**
 * Order service class
 * Encapsulates the list of orders & CRUD operations with them
 *
 * @author Sergey Iryupin
 * @version 13-May-24
 */
public class OrderService {
    private OrderDbRepository repository;
    private CrudRepository<Integer, Pizza> pizzaRepository;
    private OrderPizzaDbRepository orderPizzaRepozitory;
    private CrudRepository<Integer, ExtComponent> extComponentRepository;

    public OrderService(OrderDbRepository repository,
                        CrudRepository<Integer, Pizza> pizzaRepository,
                        OrderPizzaDbRepository orderPizzaRepozitory,
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
        OrderPizza orderPizza = orderPizzaRepozitory.save(new OrderPizza(pizza));
        order.getOrderPizzas().add(orderPizza);
        repository.addPizza(order, orderPizza);
    }

    public void deleteOrderPizza(int orderId, int orderPizzaId) {
        Order order = get(orderId);
        OrderPizza orderPizza = orderPizzaRepozitory.findById(orderPizzaId);
        order.getOrderPizzas().remove(orderPizza);
        repository.deletePizza(order, orderPizza);
    }

    public void addExtСomponent(int orderPizzaId, int extСomponentId) {
        OrderPizza orderPizza = orderPizzaRepozitory.findById(orderPizzaId);
        ExtComponent extComponent = extComponentRepository.findById(extСomponentId);
        orderPizza.getComponents().add(extComponent);
        orderPizzaRepozitory.addComponent(orderPizza, extComponent);
    }

    public void deleteExtСomponent(int orderPizzaId, int extСomponentId) {
        OrderPizza orderPizza = orderPizzaRepozitory.findById(orderPizzaId);
        ExtComponent extСomponent = extComponentRepository.findById(extСomponentId);
        orderPizza.getComponents().remove(extСomponent);
        orderPizzaRepozitory.deleteComponents(orderPizza, extСomponent);
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
