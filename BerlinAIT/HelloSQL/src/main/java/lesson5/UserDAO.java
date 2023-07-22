package lesson5;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*

Создание таблицы

CREATE TABLE users (
  id      	SERIAL PRIMARY KEY,
  name    	TEXT NOT NULL,
  password  TEXT NOT NULL
);

Добавление записей

INSERT INTO users (name, password) VALUES ('Aleksey', '*****'), ('Viktors', '***'), ('Vadim', '*');

Просмотр всех записей

SELECT * FROM users;

 */
public class UserDAO {
    private DataSource dataSource;

    public UserDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User findById(Integer id) throws SQLException {
        User user = null;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("select * from users where id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
        }

        return user;
    }

    public User findByName(String name) throws SQLException {
        User user = null;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("select * from users where name = ?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
        }

        return user;
    }
}
