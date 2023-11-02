package lesson9;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*

CREATE TABLE event (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    city TEXT NOT NULL
);

INSERT INTO event (name, city) VALUES ('Jazz Concert', 'Berlin');

 */
public class EventDAO {

    public Event findByCity(String city) throws SQLException {
        Event event = null;

        // подключение к базе данных
        try (Connection connection = PGSingltonDataSource.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM event WHERE city = ?");
            ps.setString(1, city);
            ResultSet rs = ps.executeQuery();

            // получение данных и создание объекта
            if (rs.next()) {
                event = new Event(rs.getInt("id"), rs.getString("name"), rs.getString("city"));
            }
        }

        // возврат записи (объекта)
        return event;
    }

    public Event findById(Integer id) throws SQLException {
        Event event = null;

        // подключение к базе данных
        try (Connection connection = PGSingltonDataSource.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM event WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            // получение данных и создание объекта
            if (rs.next()) {
                event = new Event(rs.getInt("id"), rs.getString("name"), rs.getString("city"));
            }
        }

        // возврат записи (объекта)
        return event;
    }
}
