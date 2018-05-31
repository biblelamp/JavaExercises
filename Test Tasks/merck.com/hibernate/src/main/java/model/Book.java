package model;

/**
 * Test task Bookshelf from merck.com
 * Class Book for the relevant table
 *
 * @author Sergey Iryupin
 * @version dated May 31, 2018
 */

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@ToString
@Entity
@Table(name="book")
public class Book {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private int authorid;

    public Book() {}

    public Book(String name, int authorid) {
        this.name = name;
        this.authorid = authorid;
    }
}