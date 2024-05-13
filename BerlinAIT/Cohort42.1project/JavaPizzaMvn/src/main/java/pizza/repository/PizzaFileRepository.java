package pizza.repository;

import pizza.domain.Pizza;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PizzaFileRepository implements CrudRepository<Integer, Pizza> {
    private String fileName;
    private final String SPLIT_CHAR = ";";

    public PizzaFileRepository(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Collection<Pizza> findAll() {
        Collection<Pizza> pizzas = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            lines.forEach(str -> {
                String[] fields = str.split(SPLIT_CHAR);
                pizzas.add(new Pizza(Integer.valueOf(fields[0]), fields[1], fields[2], Integer.valueOf(fields[3])));
            });
        } catch (NoSuchFileException e) {
            // if file not found - return empty list
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return pizzas;
    }

    @Override
    public Pizza findById(Integer id) {
        Collection<Pizza> pizzas = findAll();
        for (Pizza pizza : pizzas) {
            if (pizza.getId() == id) {
                return pizza;
            }
        }
        return null;
    }

    @Override
    public void save(Pizza pizza) {
        // read all records from file
        Collection<Pizza> pizzas = findAll();
        if (pizza.getId() == null) {
            // add record and generate id
            int id = 0;
            for (Pizza p : pizzas) { // TODO optimization
                if (id < p.getId()) {
                    id = p.getId();
                }
            }
            pizza.setId(id + 1);
            pizzas.add(pizza);
        } else {
            // update record
            for (Pizza p : pizzas) {
                if (p.getId() == pizza.getId()) {
                    p.update(pizza.getName(), pizza.getComposition(), pizza.getPrice());
                    break;
                }
            }
        }
        // rewrite all records to file
        rewiteFile(pizzas);
    }

    @Override
    public void deleteById(Integer id) {
        Collection<Pizza> pizzas = findAll();
        Pizza deletedPizza = null;
        for (Pizza pizza : pizzas) {
            if (pizza.getId() == id) {
                deletedPizza = pizza;
                break;
            }
        }
        // if pizza found by id
        if (deletedPizza != null) {
            pizzas.remove(deletedPizza);
            // rewrite all records to file
            rewiteFile(pizzas);
        }
    }

    @Override
    public void deleteAll() {
        // TODO
    }

    private void rewiteFile(Collection<Pizza> pizzas) {
        try (FileWriter writer = new FileWriter(fileName)) { // TODO Buffered
            for (Pizza p : pizzas) {
                String str = p.getId() + SPLIT_CHAR + p.getName() + SPLIT_CHAR + p.getComposition() + SPLIT_CHAR + p.getPrice();
                writer.write(str + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
