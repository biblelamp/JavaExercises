package model;

/**
 * Test task Bookshelf from merck.com
 * Class Reader for the relevant table
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


@ToString
@Entity
@Table(name="reader")
public class Reader {

    @Id
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String dateofbirth;

    @Getter
    @Setter
    private String books;

    public Reader() {}

    public Reader(int id, String name, String dateofbirth, String books) {
        this.id = id;
        this.name = name;
        this.dateofbirth = dateofbirth;
        this.books = books;
    }
}