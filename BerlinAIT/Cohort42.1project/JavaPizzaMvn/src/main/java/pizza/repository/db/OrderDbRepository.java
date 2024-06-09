package pizza.repository.db;

import pizza.domain.Customer;
import pizza.domain.Order;
import pizza.domain.OrderPizza;
import pizza.domain.OrderState;
import pizza.repository.CrudRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Order repository
 * Implementation of access methods to the Order data source
 *
 * @author Sergey Iryupin
 * @version 25-May-24
 */
public class OrderDbRepository implements CrudRepository<Integer, Order> {

    private String dbName;
    private CrudRepository<Integer, Customer> customerRepository;
    private OrderPizzaDbRepository orderPizzaRepository;

    /*
    CREATE TABLE IF NOT EXISTS `order` (
        id          INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
        customer_id INTEGER NOT NULL,
        state       TEXT NOT NULL,
        create_date TEXT NOT NULL,
        close_date  TEXT
    )
    CREATE TABLE IF NOT EXISTS order_order_pizza (
        order_id       INTEGER NOT NULL,
        order_pizza_id INTEGER NOT NULL
     )
     */

    private final String SQL_INSERT = "INSERT INTO `order` (customer_id,state,create_date) VALUES (?,?,?)";
    private final String SQL_UPDATE = "UPDATE `order` SET customer_id = ?, state = ?, close_date = ? WHERE id = ?";
    private final String SQL_FIND_BY_ID = "SELECT * FROM `order` WHERE id = ?";
    private final String SQL_DELETE_BY_ID = "DELETE FROM `order` WHERE id = ?";
    private final String SQL_FIND_ALL = "SELECT * FROM `order`";

    private final String SQL_PIZZA_INSERT = "INSERT INTO order_order_pizza (order_id,order_pizza_id) VALUES (?,?)";
    private final String SQL_PIZZA_DELETE = "DELETE FROM order_order_pizza WHERE order_id = ? AND order_pizza_id = ?";
    private final String SQL_FIND_BY_ORDER_ID = "SELECT * FROM order_order_pizza WHERE order_id = ?";

    public OrderDbRepository(final String dbName,
                             final CrudRepository<Integer, Customer> customerRepository,
                             final OrderPizzaDbRepository orderPizzaRepository) {
        this.dbName = dbName;
        this.customerRepository = customerRepository;
        this.orderPizzaRepository = orderPizzaRepository;
    }

    @Override
    public Order save(Order order) {
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
                psi.setInt(1, order.getCustomer().getId());
                psi.setString(2, order.getState().name());
                if (order.getCloseDate() != null) {
                    psi.setString(3, order.getCloseDate().toString());
                }
                psi.setInt(4, order.getId());
                psu.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return order;
    }

    @Override
    public Order findById(Integer id) {
        try (Connection connection = DriverManager.getConnection(dbName);
             PreparedStatement ps = connection.prepareStatement(SQL_FIND_BY_ID);
             PreparedStatement psp = connection.prepareStatement(SQL_FIND_BY_ORDER_ID)) {
            // read order
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return getOrderFromResultSet(rs, psp);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Order> findAll() {
        Collection<Order> orders = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(dbName);
             Statement stmt = connection.createStatement();
             PreparedStatement psp = connection.prepareStatement(SQL_FIND_BY_ORDER_ID)) {
            ResultSet rs = stmt.executeQuery(SQL_FIND_ALL);
            while (rs.next()) {
                Order order = getOrderFromResultSet(rs, psp);
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }

    private Order getOrderFromResultSet(ResultSet rs, PreparedStatement psp) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt("id"));
        Integer customerId = rs.getInt("customer_id");
        Customer customer = customerRepository.findById(customerId);
        order.setCustomer(customer);
        order.setState(OrderState.valueOf(rs.getString("state")));
        order.setCreateDate(LocalDateTime.parse(rs.getString("create_date")));
        if (rs.getString("close_date") != null) {
            order.setCloseDate(LocalDateTime.parse(rs.getString("close_date")));
        }
        // read order pizzas
        psp.setInt(1, order.getId());
        ResultSet rsp = psp.executeQuery();
        while (rsp.next()) {
            Integer orderPizzaId = rsp.getInt("order_pizza_id");
            OrderPizza orderPizza = orderPizzaRepository.findById(orderPizzaId);
            order.getOrderPizzas().add(orderPizza);
        }
        return order;
    }

    @Override
    public void deleteById(Integer id) {
        try (Connection connection = DriverManager.getConnection(dbName);
             PreparedStatement ps = connection.prepareStatement(SQL_DELETE_BY_ID)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAll() {
        throw new NullPointerException("Method not implemented");
    }

    public void addPizza(Order order, OrderPizza orderPizza) {
        try (Connection connection = DriverManager.getConnection(dbName);
             PreparedStatement psi = connection.prepareStatement(SQL_PIZZA_INSERT)) {
            psi.setInt(1, order.getId());
            psi.setInt(2, orderPizza.getId());
            psi.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletePizza(Order order, OrderPizza orderPizza) {
        try (Connection connection = DriverManager.getConnection(dbName);
             PreparedStatement psd = connection.prepareStatement(SQL_PIZZA_DELETE)) {
            psd.setInt(1, order.getId());
            psd.setInt(2, orderPizza.getId());
            psd.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
