package de.pizza.tomate.service;

import de.pizza.tomate.controller.dto.PizzaDTO;
import de.pizza.tomate.domain.PizzaBase;
import de.pizza.tomate.repository.PizzaBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PizzaService {

    @Autowired
    private PizzaBaseRepository pizzaBaseRepository;

    public List<PizzaDTO> findAll() {
        List<PizzaBase> pizzas = pizzaBaseRepository.findAll();
        List<PizzaDTO> result = new ArrayList<>(pizzas.size());
        pizzas.forEach(pizza -> result.add(PizzaDTO.getInstance(pizza)));
        return result;
    }
}
