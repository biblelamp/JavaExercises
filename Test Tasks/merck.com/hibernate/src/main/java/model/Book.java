package model;

/**
 * Test task Bookshelf from merck.com
 * Class Book for the relevant table
 *
 * @author Sergey Iryupin
 * @version dated Jun 06, 2018
 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

@AllArgsConstructor()
@ToString
@Entity
@Table(name="book")
public class Book {

    @Id
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private int authorid;
}