/**
 * Java. Level 2. Lesson 8. Homework
 * Simple network chat
 *
 * @author Sergey Iryupin
 * @version 02 Aug 2016
 */
import java.sql.*;

public class DBHandler {

    public static final String SQL_GET_NICKNAME = "SELECT Nickname FROM main where Login=? and Password=?";
    public static final String DB_CONNECTION_URL = "jdbc:sqlite:ClientsDB.db";
    public static final String DB_DRIVER = "org.sqlite.JDBC";

    static {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getNickNameByLoginAndPassword(String login, String password) {
        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL);
            PreparedStatement stmt = conn.prepareStatement(SQL_GET_NICKNAME)) {

            stmt.setString(1, login);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("Nickname");
            } else {
                throw new HandShakeException("can't find use with login=" + login);
            }
        } catch (SQLException e) {
            throw new HandShakeException(e.getMessage(), e);
        }
    }
}