package de.pizza.tomate.controller;

import de.pizza.tomate.controller.dto.PizzaTypeDTO;
import de.pizza.tomate.service.PizzaTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/type")
public class PizzaTypeController {

    @Autowired
    private PizzaTypeService pizzaTypeService;

    @GetMapping("/all")
    public List<PizzaTypeDTO> findAll() {
        return pizzaTypeService.findAll();
    }

    @PostMapping("/add")
    public PizzaTypeDTO add(@RequestBody PizzaTypeDTO pizzaTypeDTO) {
        return pizzaTypeService.add(pizzaTypeDTO);
    }

    @PutMapping("/update")
    public PizzaTypeDTO update(@RequestBody PizzaTypeDTO pizzaTypeDTO) {
        return pizzaTypeService.update(pizzaTypeDTO);
    }

    @DeleteMapping("/delete/{id}")
    public PizzaTypeDTO delete(@PathVariable Integer id) {
        return pizzaTypeService.delete(id);
    }
}
