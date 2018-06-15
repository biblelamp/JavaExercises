package model;

/**
 * Test task Bookshelf from merck.com
 * Class Author for the relevant table
 *
 * @author Sergey Iryupin
 * @version dated May 31, 2018
 */

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@RequiredArgsConstructor
@ToString
@Entity
@Table(name="author")
public class Author {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    @NonNull
    private String name;
}