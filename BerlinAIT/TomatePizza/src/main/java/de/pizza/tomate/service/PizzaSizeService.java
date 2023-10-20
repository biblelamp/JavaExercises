package de.pizza.tomate.service;

import de.pizza.tomate.controller.dto.PizzaSizeDTO;
import de.pizza.tomate.domain.PizzaSize;
import de.pizza.tomate.repository.IngredientRepository;
import de.pizza.tomate.repository.PizzaBaseRepository;
import de.pizza.tomate.repository.PizzaSizeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class PizzaSizeService {

    @Autowired
    private PizzaSizeRepository pizzaSizeRepository;

    @Autowired
    private PizzaBaseRepository pizzaBaseRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    public List<PizzaSizeDTO> findAll() {
        List<PizzaSize> sizes = pizzaSizeRepository.findAll();
        List<PizzaSizeDTO> result = new ArrayList<>(sizes.size());
        sizes.forEach(size -> result.add(PizzaSizeDTO.getInstance(size)));
        return result;
    }

    public PizzaSizeDTO add(PizzaSizeDTO pizzaSizeDTO) {
        PizzaSize pizzaSize = new PizzaSize(null, pizzaSizeDTO.getName(), pizzaSizeDTO.getSize());
        return PizzaSizeDTO.getInstance(pizzaSizeRepository.save(pizzaSize));
    }

    public PizzaSizeDTO update(PizzaSizeDTO pizzaSizeDTO) {
        PizzaSize pizzaSize = pizzaSizeRepository.findById(pizzaSizeDTO.getId()).orElse(null);
        if (pizzaSize != null) {
            // if this size not used in Ingredient AND PizzaBase tables
            if (ingredientRepository.countByPizzaSize(pizzaSize) == 0
                    && pizzaBaseRepository.countByPizzaSize(pizzaSize) == 0) {
                pizzaSize.setName(pizzaSizeDTO.getName());
                pizzaSize.setSize(pizzaSizeDTO.getSize());
                return PizzaSizeDTO.getInstance(pizzaSizeRepository.save(pizzaSize));
            }
            log.error("Item PizzaSize used, cannot update pizzaSizeId={}", pizzaSize.getId());
        }
        return null;
    }

    public PizzaSizeDTO delete(Integer id) {
        PizzaSize pizzaSize = pizzaSizeRepository.findById(id).orElse(null);
        if (pizzaSize != null) {
            // if this size not used in Ingredient AND PizzaBase tables
            if (ingredientRepository.countByPizzaSize(pizzaSize) == 0
                    && pizzaBaseRepository.countByPizzaSize(pizzaSize) == 0) {
                pizzaSizeRepository.delete(pizzaSize);
                return PizzaSizeDTO.getInstance(pizzaSize);
            }
            log.error("Item PizzaSize used, cannot delete pizzaSizeId={}", pizzaSize.getId());
        }
        return null;
    }
}
