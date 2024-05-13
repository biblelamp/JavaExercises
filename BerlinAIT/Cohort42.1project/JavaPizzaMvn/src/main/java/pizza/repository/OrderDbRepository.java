package pizza.repository;

import pizza.domain.Customer;
import pizza.domain.Order;
import pizza.domain.OrderState;
import pizza.domain.Pizza;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

public class OrderDbRepository implements CrudRepository<Integer, Order> {

    private String dbName;
    private CrudRepository<Integer, Customer> customerRepository;
    private CrudRepository<Integer, Pizza> pizzaRepository;

    /* CREATE TABLE IF NOT EXISTS `order` (
        id          INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
        customer_id INTEGER NOT NULL,
        state       TEXT NOT NULL,
        create_date TEXT NOT NULL,
        close_date  TEXT
    ) */
    /* CREATE TABLE IF NOT EXISTS order_pizza (
        order_id    INTEGER NOT NULL,
        pizza_id    INTEGER NOT NULL
     ) */

    private final String SQL_INSERT = "INSERT INTO `order` (customerId,state,create_date) VALUES (?,?,?)";
    private final String SQL_UPDATE = "UPDATE `order` SET customer_id = ?, state = ?, close_date = ? WHERE id = ?";
    private final String SQL_FIND_BY_ID = "SELECT * FROM `order` WHERE id = ?";
    private final String SQL_DELETE_BY_ID = "DELETE FROM `order` WHERE id = ?";
    private final String SQL_FIND_ALL = "SELECT * FROM `order`";

    private final String SQL_PIZZA_INSERT = "INSERT INTO order_pizza (order_id,pizza_id) VALUES (?,?)";
    private final String SQL_PIZZA_DELETE = "DELETE FROM order_pizza WHERE order_id = ? AND pizza_id = ?";
    private final String SQL_FIND_BY_ORDER_ID = "SELECT * FROM order_pizza WHERE order_id = ?";

    public OrderDbRepository(final String dbName,
                             final CrudRepository<Integer, Customer> customerRepository,
                             CrudRepository<Integer, Pizza> pizzaRepository) {
        this.dbName = dbName;
        this.customerRepository = customerRepository;
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public void save(Order order) {
        try (Connection connection = DriverManager.getConnection(dbName);
             PreparedStatement psi = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement psu = connection.prepareStatement(SQL_UPDATE)) {
            if (order.getId() == null) {
                // insert record
                psi.setInt(1, order.getCustomer().getId());
                psi.setString(2, order.getState().name());
                psi.setString(3, order.getCreateDate().toString());
                psi.executeUpdate();

                ResultSet rs = psi.getGeneratedKeys();
                if (rs.next()) {
                    order.setId(rs.getInt(1));
                }
            } else {
                // update record
                //psu.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Order findById(Integer id) {
        Order order = null;
        try (Connection connection = DriverManager.getConnection(dbName);
             PreparedStatement ps = connection.prepareStatement(SQL_FIND_BY_ID);
             PreparedStatement psp = connection.prepareStatement(SQL_FIND_BY_ORDER_ID)) {
            // read order
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                order = new Order();
                order.setId(rs.getInt("id"));
                Integer customerId = rs.getInt("customer_id");
                Customer customer = customerRepository.findById(customerId);
                order.setCustomer(customer);
                order.setState(OrderState.valueOf(rs.getString("state")));
                order.setCreateDate(LocalDateTime.parse(rs.getString("create_date")));
                if (rs.getString("close_date") != null) {
                    order.setCloseDate(LocalDateTime.parse(rs.getString("close_date")));
                }
            }
            // read pizzas
            psp.setInt(1, order.getId());
            ResultSet rsp = psp.executeQuery();
            while (rsp.next()) {
                Integer pizzaId = rsp.getInt("pizza_id");
                Pizza pizza = pizzaRepository.findById(pizzaId);
                order.getOrderPizzas().add(pizza);
            }
            return order;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Order> findAll() {
        // TODO
        return Collections.emptyList();
    }

    @Override
    public void deleteById(Integer id) {
        // TODO
    }

    @Override
    public void deleteAll() {
        throw new NullPointerException("Method not implemented");
    }

    public void addPizza(Order order, Pizza pizza) {
        try (Connection connection = DriverManager.getConnection(dbName);
             PreparedStatement psi = connection.prepareStatement(SQL_PIZZA_INSERT)) {
            psi.setInt(1, order.getId());
            psi.setInt(2, pizza.getId());
            psi.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletePizza(Order order, Pizza pizza) {
        try (Connection connection = DriverManager.getConnection(dbName);
             PreparedStatement psd = connection.prepareStatement(SQL_PIZZA_DELETE)) {
            psd.setInt(1, order.getId());
            psd.setInt(2, pizza.getId());
            psd.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
