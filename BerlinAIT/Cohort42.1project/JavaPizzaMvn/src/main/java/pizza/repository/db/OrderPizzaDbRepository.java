package pizza.repository.db;

import pizza.domain.ExtComponent;
import pizza.domain.OrderPizza;
import pizza.domain.Pizza;
import pizza.repository.CrudRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Order pizza repository
 * Implementation of access methods to the Order pizza data source
 *
 * @author Sergey Iryupin
 * @version 02-Jun-24
 */
public class OrderPizzaDbRepository implements CrudRepository<Integer, OrderPizza> {

    private String dbName;
    private CrudRepository<Integer, Pizza> pizzaRepository;
    private CrudRepository<Integer, ExtComponent> componentRepository;
    /*
    CREATE TABLE IF NOT EXISTS order_pizza (
        id       INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
        pizza_id INTEGER NOT NULL
    )
    CREATE TABLE IF NOT EXISTS order_pizza_component (
        order_pizza_id INTEGER NOT NULL,
        component_id   INTEGER NOT NULL
    )
    */

    private final String SQL_INSERT = "INSERT INTO order_pizza (pizza_id) VALUES (?)";
    private final String SQL_UPDATE = "UPDATE order_pizza SET pizza_id = ? WHERE id = ?";
    private final String SQL_FIND_BY_ID = "SELECT * FROM order_pizza WHERE id = ?";
    private final String SQL_DELETE_BY_ID = "DELETE FROM order_pizza WHERE id = ?";
    private final String SQL_FIND_ALL = "SELECT * FROM order_pizza";

    private final String SQL_COMPONENT_INSERT = "INSERT INTO order_pizza_component (order_pizza_id,component_id) VALUES (?,?)";
    private final String SQL_COMPONENT_DELETE = "DELETE FROM order_pizza_component WHERE order_pizza_id = ? AND component_id = ?";
    private final String SQL_FIND_BY_ORDER_PIZZA_ID = "SELECT * FROM order_pizza_component WHERE order_pizza_id = ?";

    public OrderPizzaDbRepository(String dbName,
                                  CrudRepository<Integer, Pizza> pizzaRepository,
                                  CrudRepository<Integer, ExtComponent> componentRepository) {
        this.dbName = dbName;
        this.pizzaRepository = pizzaRepository;
        this.componentRepository = componentRepository;
    }

    @Override
    public OrderPizza save(OrderPizza orderPizza) {
        try (Connection connection = DriverManager.getConnection(dbName);
             PreparedStatement psi = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement psu = connection.prepareStatement(SQL_UPDATE)) {
            if (orderPizza.getId() == null) {
                psi.setInt(1, orderPizza.getPizza().getId());
                psi.executeUpdate();

                ResultSet rs = psi.getGeneratedKeys();
                if (rs.next()) {
                    orderPizza.setId(rs.getInt(1));
                }
            } else {
                // TODO update?
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderPizza;
    }

    @Override
    public OrderPizza findById(Integer id) {
        OrderPizza orderPizza = null;
        try (Connection connection = DriverManager.getConnection(dbName);
             PreparedStatement ps = connection.prepareStatement(SQL_FIND_BY_ID);
             PreparedStatement psc = connection.prepareStatement(SQL_FIND_BY_ORDER_PIZZA_ID)) {
            // read order pizza
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Integer pizzaId = rs.getInt("pizza_id");
                Pizza pizza = pizzaRepository.findById(pizzaId);
                orderPizza = new OrderPizza(pizza);
                orderPizza.setId(id);
                // read components
                psc.setInt(1, orderPizza.getId());
                ResultSet rsc = psc.executeQuery();
                while (rsc.next()) {
                    Integer componentId = rsc.getInt("component_id");
                    ExtComponent component = componentRepository.findById(componentId);
                    orderPizza.getComponents().add(component);
                }
            }
            return orderPizza;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<OrderPizza> findAll() {
        Collection<OrderPizza> orderPizzas = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(dbName);
             Statement stmt = connection.createStatement();
             PreparedStatement psp = connection.prepareStatement(SQL_FIND_BY_ORDER_PIZZA_ID)) {
            ResultSet rs = stmt.executeQuery(SQL_FIND_ALL);
            while (rs.next()) {
                //OrderPizza orderPizza = getOrderFromResultSet(rs, psp);
                //orderPizzas.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderPizzas;
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

    public void addComponent(OrderPizza orderPizza, ExtComponent extComponent) {
        try (Connection connection = DriverManager.getConnection(dbName);
             PreparedStatement psi = connection.prepareStatement(SQL_COMPONENT_INSERT)) {
            psi.setInt(1, orderPizza.getId());
            psi.setInt(2, extComponent.getId());
            psi.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteComponents(OrderPizza orderPizza, ExtComponent extСomponent) {
        try (Connection connection = DriverManager.getConnection(dbName);
             PreparedStatement psd = connection.prepareStatement(SQL_COMPONENT_DELETE)) {
            psd.setInt(1, orderPizza.getId());
            psd.setInt(2, extСomponent.getId());
            psd.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
