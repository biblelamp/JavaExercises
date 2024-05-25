package pizza.repository;

import pizza.domain.Customer;
import pizza.domain.OrderPizza;
import pizza.domain.Pizza;

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

    private final String SQL_INSERT = "INSERT INTO order_pizza (pizzaId) VALUES (?)";
    private final String SQL_UPDATE = "UPDATE order_pizza SET pizza_id = ? WHERE id = ?";
    private final String SQL_FIND_BY_ID = "SELECT * FROM order_pizza WHERE id = ?";
    private final String SQL_DELETE_BY_ID = "DELETE FROM order_pizza WHERE id = ?";
    private final String SQL_FIND_ALL = "SELECT * FROM order_pizza";

    public OrderPizzaDbRepository(String dbName) {
        this.dbName = dbName;
    }

    @Override
    public void save(OrderPizza orderPizza) {
        try (Connection connection = DriverManager.getConnection(dbName);
             PreparedStatement psi = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement psu = connection.prepareStatement(SQL_UPDATE)) {
            if (orderPizza.getId() == null) {

            } else {

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public OrderPizza findById(Integer id) {
        return null;
    }

    @Override
    public Collection<OrderPizza> findAll() {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void deleteAll() {
        throw new NullPointerException("Method not implemented");
    }
}
