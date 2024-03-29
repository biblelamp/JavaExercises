package de.pizza.tomate.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PizzaBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pizza_base_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "pizza_type_id")
    private PizzaType pizzaType;

    @ManyToOne
    @JoinColumn(name = "pizza_size_id")
    private PizzaSize pizzaSize;

    private Double price;

    // if this pizza was ordered
    private Boolean ordered;

    private Boolean deleted;
}
