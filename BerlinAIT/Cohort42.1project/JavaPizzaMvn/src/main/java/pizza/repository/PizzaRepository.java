package pizza.repository;

import pizza.domain.Pizza;

import java.sql.*;
import java.util.*;

/**
 * Pizza repository
 * Implementation of access methods to the Pizza data source
 *
 * @author Sergey Iryupin
 * @version 08-May-24
 */
public class PizzaRepository implements CrudRepository<Integer, Pizza> {
    //private Map<Integer, Pizza> pizzaMap;
    private String dbName;

    private final String SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS pizza (" +
                    " id          INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    " name        TEXT NOT NULL," +
                    " composition TEXT NOT NULL," +
                    " price       INTEGER NOT NULL)";
    private final String SQL_INSERT = "INSERT INTO pizza (name,composition,price) VALUES (?,?,?)";
    private final String SQL_UPDATE = "UPDATE pizza SET name = ?, composition = ?, price = ? WHERE id = ?";
    private final String SQL_FIND_BY_ID = "SELECT * FROM pizza WHERE id = ?";
    private final String SQL_FIND_ALL = "SELECT * FROM pizza";
    private final String SQL_DELETE_BY_ID = "DELETE FROM pizza WHERE id = ?";
    public PizzaRepository(String dbName) {
        this.dbName = dbName;
    }

//    public PizzaRepository() {
//        pizzaMap = new HashMap<>();
//    }

    @Override
    public void save(Pizza pizza) {
        //pizzaMap.put(pizza.getId(), pizza);
        try (Connection connection = DriverManager.getConnection(dbName);
             PreparedStatement psi = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement psu = connection.prepareStatement(SQL_UPDATE)) {
            if (pizza.getId() == null) {
                // insert record
                psi.setString(1, pizza.getName());
                psi.setString(2, pizza.getComposition());
                psi.setInt(3, pizza.getPrice());
                psi.executeUpdate();

                ResultSet rs = psi.getGeneratedKeys();
                if (rs.next()) {
                    pizza.setId(rs.getInt(1));
                }
            } else {
                // update record
                psu.setString(1, pizza.getName());
                psu.setString(2, pizza.getComposition());
                psu.setInt(3, pizza.getPrice());
                psu.setInt(4, pizza.getId());
                psu.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Pizza findById(Integer id) {
        //return pizzaMap.get(id);
        Pizza pizza = null;
        try (Connection connection = DriverManager.getConnection(dbName);
             PreparedStatement ps = connection.prepareStatement(SQL_FIND_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                pizza = new Pizza(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("composition"),
                        rs.getInt("price"));
            }
            return pizza;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(Integer id) {
        //pizzaMap.remove(id);
        try (Connection connection = DriverManager.getConnection(dbName);
             PreparedStatement ps = connection.prepareStatement(SQL_DELETE_BY_ID)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Pizza> findAll() {
        //return pizzaMap.values();
        Collection<Pizza> pizzas = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(dbName);
             Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(SQL_FIND_ALL);
            while (rs.next()) {
                pizzas.add(new Pizza(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("composition"),
                        rs.getInt("price")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pizzas;
    }

    public void init() {
        List<Pizza> pizzas = new ArrayList<>(List.of(
                new Pizza("Margherita", "tomato base, mozzarella, basil", 174),
                new Pizza("Capriciosa", "tomato base, mozzarella, ham, champignons, olives", 190),
                new Pizza("Hawai", "tomato base, mozzarella, ham, pineapple", 190),
                new Pizza("Salami", "tomato base, mozzarella, salami, grana padano", 194),
                new Pizza("Quattro Formaggi", "cream base, mozzarella, grana padano, gorgonzola, smoked cheese", 204)
        ));
        //pizzaMap = pizzas.stream().collect(Collectors.toMap(Pizza::getId, p -> p));
        pizzas.forEach(pizza -> save(pizza));
    }
}
