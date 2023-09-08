package lesson6;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
        // connect and get data
        User user = null;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
        }
        return user;
    }

    public List<User> findAll() {
        // TODO home work #6
        // SELECT * FROM users;
        return null;
    }

    public User add(User user) {
        // TODO home work #6
        // INSERT INTO users (name, password) VALUES ('User', 'passwd')
        return null;
    }

    public User update(User user) {
        // TODO home work #6
        // UPDATE users SET name = 'newName', password = 'newPasswd' WHERE id = ?
        return null;
    }

    public User delete(Integer id) {
        // TODO home work #6
        // DELETE FROM user WHERE id = ?
        return null;
    }
}
