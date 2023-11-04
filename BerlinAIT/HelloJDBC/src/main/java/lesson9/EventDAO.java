package lesson9;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*

CREATE TABLE event (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    city TEXT NOT NULL
);

INSERT INTO event (name, city) VALUES ('Jazz Concert', 'Berlin') RETURNING id;

by https://stackoverflow.com/questions/2944297/postgresql-function-for-last-inserted-id

 */
public class EventDAO {

    private final DataSource dataSource;

    public EventDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Event remove(Integer id) throws SQLException {
        Event event = null;

        // подключение к базе данных
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM event WHERE id = ? IS TRUE RETURNING *");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            // получение данных и создание объекта
            if (rs.next()) {
                event = new Event(rs.getInt("id"), rs.getString("name"), rs.getString("city"));
            }
            // TODO throw if id not exists
        }
        return event;
    }

    public Event save(Event event) throws SQLException {
        PreparedStatement ps;

        // подключение к базе данных
        try (Connection connection = dataSource.getConnection()) {
            if (event.getId() == null) {
                ps = connection.prepareStatement("INSERT INTO event (name, city) VALUES (?, ?) RETURNING id");
                ps.setString(1, event.getName());
                ps.setString(2, event.getCity());
                ResultSet rs = ps.executeQuery();

                // получение данных и установка id
                if (rs.next()) {
                    event.setId(rs.getInt("id"));
                }
            } else {
                ps = connection.prepareStatement("UPDATE event SET name = ?, city = ? WHERE id = ?");
                ps.setString(1, event.getName());
                ps.setString(2, event.getCity());
                ps.setInt(3, event.getId());

                int rowCount = ps.executeUpdate();
                System.out.println(rowCount);
                // TODO throw if id not exists
            }
        }

        return event;
    }

    public List<Event> findAll() throws SQLException {
        List<Event> events = new ArrayList<>();

        // подключение к базе данных
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM event");
            ResultSet rs = ps.executeQuery();

            // получение данных и создание объекта
            while (rs.next()) {
                events.add(new Event(rs.getInt("id"), rs.getString("name"), rs.getString("city")));
            }
        }

        // возврат записей (объектов)
        return events;
    }

    public Event findById(Integer id) throws SQLException {
        Event event = null;

        // подключение к базе данных
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM event WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            // получение данных и создание объекта
            if (rs.next()) {
                event = new Event(rs.getInt("id"), rs.getString("name"), rs.getString("city"));
            }
            if (event == null) {
                throw new SQLDataException("No found record in event table, id=" + id);
            }
        }

        // возврат записи (объекта)
        return event;
    }
}
