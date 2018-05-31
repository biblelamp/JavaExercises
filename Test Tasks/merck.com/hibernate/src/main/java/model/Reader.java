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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@ToString
@Entity
@Table(name="reader")
public class Reader {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date dateofbirth;

    @Getter
    @Setter
    private String books;

    public Reader() {}

    public Reader(String name, Date dateofbirth, String books) {
        this.name = name;
        this.dateofbirth = dateofbirth;
        this.books = books;
    }
}