package lesson5;

import java.sql.SQLException;

public class UserDAODemo {
    public static void main(String[] args) throws SQLException {
        UserDAO userDAO = new UserDAO(MyDataSource.getDataSource());
        User user;
        for (int i = 1; i < 4; i++) {
            user = userDAO.findById(i);
            System.out.println(user);
        }
        user = userDAO.findByName("Viktors");
        System.out.println(user);
    }
}
