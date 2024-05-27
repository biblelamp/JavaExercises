package pizza.repository.db;

import pizza.domain.Customer;
import pizza.domain.ExtComponent;
import pizza.domain.OrderPizza;
import pizza.domain.Pizza;
import pizza.repository.CrudRepository;

import java.sql.*;
import java.util.Collection;

public class OrderPizzaDbRepository implements CrudRepository<Integer, OrderPizza> {

    private String dbName;
    private CrudRepository<Integer, Pizza> pizzaRepository;
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

    public OrderPizzaDbRepository(String dbName, CrudRepository<Integer, Pizza> pizzaRepository) {
        this.dbName = dbName;
        this.pizzaRepository = pizzaRepository;
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
             PreparedStatement ps = connection.prepareStatement(SQL_FIND_BY_ID)) {
            // read order
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Integer pizzaId = rs.getInt("pizza_id");
                Pizza pizza = pizzaRepository.findById(pizzaId);
                orderPizza = new OrderPizza(pizza);
                orderPizza.setId(id);
                // TODO read components
            }
            return orderPizza;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<OrderPizza> findAll() {
        return null;
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
        // TODO
    }

    public void deleteComponents(OrderPizza orderPizza, ExtComponent extСomponent) {
        // TODO
    }
}
