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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PizzaSize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pizza_size_id")
    private int id;

    private String name;
    private double size;
}
