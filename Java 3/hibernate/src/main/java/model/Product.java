package model;

/**
 * Java. Level 3. Lesson 2. Homework+
 * Class Product for the relevant table
 *
 * @author Sergey Iryupin
 * @version dated Jul 13, 2018
 */

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.NonNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    @NonNull
    private String title;

    @Getter
    @Setter
    @NonNull
    private float price;
}