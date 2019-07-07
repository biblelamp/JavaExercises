package cz.javageek.tictactoe.domain;

import javax.persistence.*;

@Entity
@Table(name = "match")
public class Match {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "field", length = 9)
    private String field;

    @Column(name = "next", length = 1)
    private String next;

    @Enumerated(EnumType.STRING)
    private MatchStatus status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public MatchStatus getStatus() {
        return status;
    }

    public void setStatus(MatchStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", field='" + field + "'" +
                ", next='" + next + "'" +
                ", status='" + status + "'" +
                "}";
    }
}
