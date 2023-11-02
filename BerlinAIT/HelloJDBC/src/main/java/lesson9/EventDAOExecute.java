package lesson9;

import java.sql.SQLException;

public class EventDAOExecute {
    public static void main(String[] args) throws SQLException {
        EventDAO eventDAO = new EventDAO();

        Event event = eventDAO.findById(1);
        System.out.println(event);

        event = eventDAO.findByCity("Prague");
        System.out.println(event);
    }
}
