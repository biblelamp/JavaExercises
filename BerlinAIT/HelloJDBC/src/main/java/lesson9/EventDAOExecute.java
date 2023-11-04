package lesson9;

import java.sql.SQLException;
import java.util.List;

public class EventDAOExecute {
    public static void main(String[] args) throws SQLException {
        EventDAO eventDAO = new EventDAO(PGSingletonDataSource.getInstance());

        // findAll
        List<Event> events = eventDAO.findAll();
        System.out.println(events);

        // findById
        Event event = eventDAO.findById(3);
        System.out.println(event);

        // update (save)
        event.setName("Piano Concert");
        event.setCity("London");
        event = eventDAO.save(event);
        System.out.println(event);

        // add (save)
        Event newEvent = new Event("Piano Concert", "Parise");
        event = eventDAO.save(newEvent);
        System.out.println(event);

        events = eventDAO.findAll();
        System.out.println(events);

        // remove
        event = eventDAO.remove(event.getId());
        System.out.println(event);

        events = eventDAO.findAll();
        System.out.println(events);
    }
}
