package de.pizza.tomate.service;

import de.pizza.tomate.controller.dto.PizzaSizeDTO;
import de.pizza.tomate.controller.dto.PizzaTypeDTO;
import de.pizza.tomate.domain.PizzaType;
import de.pizza.tomate.repository.PizzaBaseRepository;
import de.pizza.tomate.repository.PizzaTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class PizzaTypeService {

    @Autowired
    private PizzaTypeRepository pizzaTypeRepository;

    @Autowired
    private PizzaBaseRepository pizzaBaseRepository;

    public List<PizzaTypeDTO> findAll() {
        List<PizzaType> types = pizzaTypeRepository.findAll();
        List<PizzaTypeDTO> result = new ArrayList<>(types.size());
        types.forEach(type -> result.add(PizzaTypeDTO.getInstance(type)));
        return result;
    }

    public PizzaTypeDTO add(PizzaTypeDTO pizzaTypeDTO) {
        PizzaType pizzaType = new PizzaType(null, pizzaTypeDTO.getName(),
                pizzaTypeDTO.getDescription(), pizzaTypeDTO.getPathImage());
        return PizzaTypeDTO.getInstance(pizzaTypeRepository.save(pizzaType));
    }

    public PizzaTypeDTO update(PizzaTypeDTO pizzaTypeDTO) {
        PizzaType pizzaType = pizzaTypeRepository.findById(pizzaTypeDTO.getId()).orElse(null);
        if (pizzaType != null) {
            if (pizzaBaseRepository.countByPizzaType(pizzaType) == 0) {
                pizzaType.setName(pizzaTypeDTO.getName());
                pizzaType.setDescription(pizzaTypeDTO.getDescription());
                pizzaType.setPathImage(pizzaTypeDTO.getPathImage());
                return PizzaTypeDTO.getInstance(pizzaTypeRepository.save(pizzaType));
            }
            log.error("Item PizzaType used, cannot update pizzaTypeId={}", pizzaTypeDTO.getId());
        }
        return null;
    }

    public PizzaTypeDTO delete(Integer id) {
        PizzaType pizzaType = pizzaTypeRepository.findById(id).orElse(null);
        if (pizzaType != null) {
            if (pizzaBaseRepository.countByPizzaType(pizzaType) == 0) {
                pizzaTypeRepository.delete(pizzaType);
                return PizzaTypeDTO.getInstance(pizzaType);
            }
            log.error("Item PizzaType used, cannot delete pizzaTypeId={}", id);
        }
        return null;
    }
}
