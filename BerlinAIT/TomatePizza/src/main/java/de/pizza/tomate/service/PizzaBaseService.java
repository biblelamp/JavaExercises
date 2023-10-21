package de.pizza.tomate.service;

import de.pizza.tomate.controller.dto.PizzaBaseCreateUpdate;
import de.pizza.tomate.controller.dto.PizzaBaseDTO;
import de.pizza.tomate.domain.PizzaBase;
import de.pizza.tomate.domain.PizzaSize;
import de.pizza.tomate.domain.PizzaType;
import de.pizza.tomate.repository.PizzaBaseRepository;
import de.pizza.tomate.repository.PizzaSizeRepository;
import de.pizza.tomate.repository.PizzaTypeRepository;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PizzaBaseService {

    @Autowired
    private PizzaBaseRepository pizzaBaseRepository;

    @Autowired
    private PizzaTypeRepository pizzaTypeRepository;

    @Autowired
    private PizzaSizeRepository pizzaSizeRepository;

    public List<PizzaBaseDTO> findAll() {
        List<PizzaBase> pizzas = pizzaBaseRepository.findAll();
        List<PizzaBaseDTO> result = new ArrayList<>(pizzas.size());
        pizzas.forEach(pizza -> result.add(PizzaBaseDTO.getInstance(pizza)));
        return result;
    }

    public PizzaBaseDTO findById(Integer id) {
        Validate.notNull(id, "pizzaBaseId can't be null!");
        PizzaBase pizza = pizzaBaseRepository.findById(id).orElse(null);
        if (pizza != null) {
            return PizzaBaseDTO.getInstance(pizza);
        }
        return null;
    }

    public List<PizzaBaseDTO> findByNameOrDesc(String str) {
        String findString = '%' + str.toLowerCase() + '%';
        List<PizzaBase> pizzas = pizzaBaseRepository.findByNameOrDesc(findString);
        List<PizzaBaseDTO> result = new ArrayList<>(pizzas.size());
        pizzas.forEach(pizza -> result.add(PizzaBaseDTO.getInstance(pizza)));
        return result;
    }

    public PizzaBaseDTO add(PizzaBaseCreateUpdate pizza) {
        Validate.notNull(pizza, "Object pizza can't be null!");
        Validate.notNull(pizza.getPizzaTypeId(), "Field pizzaTypeId can't be null!");
        Validate.notNull(pizza.getPizzaSizeId(), "Field pizzaSizeId can't be null!");
        Validate.notNull(pizza.getPrice(), "Field price can't be null!");

        PizzaType pizzaType = pizzaTypeRepository.findById(pizza.getPizzaTypeId()).orElse(null);
        PizzaSize pizzaSize = pizzaSizeRepository.findById(pizza.getPizzaSizeId()).orElse(null);
        if (pizzaType != null && pizzaSize != null) {
            PizzaBase pizzaBase = new PizzaBase();
            pizzaBase.setPizzaType(pizzaType);
            pizzaBase.setPizzaSize(pizzaSize);
            pizzaBase.setPrice(pizza.getPrice());
            pizzaBase.setOrdered(false);
            pizzaBase.setDeleted(false);
            return PizzaBaseDTO.getInstance(pizzaBaseRepository.save(pizzaBase));
        }
        return null;
    }

    public PizzaBaseDTO update(PizzaBaseCreateUpdate pizza) {
        PizzaBase pizzaBase = pizzaBaseRepository.findById(pizza.getId()).orElse(null);
        PizzaType pizzaType = pizzaTypeRepository.findById(pizza.getPizzaTypeId()).orElse(null);
        PizzaSize pizzaSize = pizzaSizeRepository.findById(pizza.getPizzaSizeId()).orElse(null);
        // if pizza exists
        if (pizzaBase != null) {
            // if pizza was ordered
            if (pizzaBase.getOrdered()) {
                pizzaBase.setPrice(pizza.getPrice());
                return PizzaBaseDTO.getInstance(pizzaBaseRepository.save(pizzaBase));
                // if pizza was NOT ordered
            } else if (pizzaType != null && pizzaSize != null) {
                pizzaBase.setPizzaType(pizzaType);
                pizzaBase.setPizzaSize(pizzaSize);
                pizzaBase.setPrice(pizza.getPrice());
                return PizzaBaseDTO.getInstance(pizzaBaseRepository.save(pizzaBase));
            }
        }
        return null;
    }

    public PizzaBaseDTO recover(Integer id) {
        PizzaBase pizzaBase = pizzaBaseRepository.findById(id).orElse(null);
        if (pizzaBase != null) {
            pizzaBase.setDeleted(false);
            return PizzaBaseDTO.getInstance(pizzaBaseRepository.save(pizzaBase));
        }
        return null;
    }

    public PizzaBaseDTO delete(Integer id) {
        PizzaBase pizzaBase = pizzaBaseRepository.findById(id).orElse(null);
        if (pizzaBase != null && !pizzaBase.getDeleted()) {
            if (pizzaBase.getOrdered()) {
                pizzaBase.setDeleted(true);
                pizzaBase = pizzaBaseRepository.save(pizzaBase);
            } else {
                pizzaBaseRepository.delete(pizzaBase);
            }
            return PizzaBaseDTO.getInstance(pizzaBase);
        }
        return null;
    }
}
