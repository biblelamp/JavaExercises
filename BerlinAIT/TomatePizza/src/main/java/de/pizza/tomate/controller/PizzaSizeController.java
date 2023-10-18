package de.pizza.tomate.controller;

import de.pizza.tomate.controller.dto.PizzaSizeDTO;
import de.pizza.tomate.service.PizzaSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/size")
public class PizzaSizeController {

    @Autowired
    private PizzaSizeService pizzaSizeService;

    @GetMapping("/all")
    public List<PizzaSizeDTO> findAll() {
        return pizzaSizeService.findAll();
    }

    @PostMapping("/add")
    public PizzaSizeDTO add(@RequestBody PizzaSizeDTO pizzaSize) {
        return pizzaSizeService.add(pizzaSize);
    }

    @PutMapping("/update")
    public PizzaSizeDTO update(@RequestBody PizzaSizeDTO pizzaSize) {
        return pizzaSizeService.update(pizzaSize);
    }

    @DeleteMapping("/delete/{id}")
    public PizzaSizeDTO delete(@PathVariable Integer id) {
        return pizzaSizeService.delete(id);
    }
}
