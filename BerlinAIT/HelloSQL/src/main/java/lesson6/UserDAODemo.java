package lesson6;

import java.sql.SQLException;

public class UserDAODemo {
    public static void main(String[] args) throws SQLException {
        UserDAO userDAO = new UserDAO(MyDataSource.getDataSource());
        User user = userDAO.findById(1);
        System.out.println(user);
    }
}
