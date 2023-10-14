package de.pizza.tomate.controller;

import de.pizza.tomate.controller.dto.PizzaDTO;
import de.pizza.tomate.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pizza")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @GetMapping("/all")
    public List<PizzaDTO> getAll() {
        return pizzaService.findAll();
    }
}
