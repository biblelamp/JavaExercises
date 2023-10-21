package de.pizza.tomate.controller;

import de.pizza.tomate.controller.dto.PizzaBaseCreateUpdate;
import de.pizza.tomate.controller.dto.PizzaBaseDTO;
import de.pizza.tomate.service.PizzaBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pizza")
public class PizzaBaseController {

    @Autowired
    private PizzaBaseService pizzaBaseService;

    @GetMapping("/all")
    public List<PizzaBaseDTO> findAll() {
        return pizzaBaseService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PizzaBaseDTO> findById(@PathVariable Integer id) {
        PizzaBaseDTO pizza = pizzaBaseService.findById(id);
        if (pizza == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pizza);
    }

    @GetMapping("/search/{str}")
    public List<PizzaBaseDTO> findByNameOrDesc(@PathVariable String str) {
        return pizzaBaseService.findByNameOrDesc(str);
    }

    @PostMapping("/add")
    public PizzaBaseDTO add(@RequestBody PizzaBaseCreateUpdate pizza) {
        return pizzaBaseService.add(pizza);
    }

    @PutMapping("/update")
    public PizzaBaseDTO update(@RequestBody PizzaBaseCreateUpdate pizza) {
        // TODO use ResponseEntity
        return pizzaBaseService.update(pizza);
    }

    @PutMapping("/recover/{id}")
    public PizzaBaseDTO recover(@PathVariable Integer id) {
        // TODO use ResponseEntity
        return pizzaBaseService.recover(id);
    }

    @DeleteMapping("/delete/{id}")
    public PizzaBaseDTO delete(@PathVariable Integer id) {
        // TODO use ResponseEntity
        return pizzaBaseService.delete(id);
    }
}
