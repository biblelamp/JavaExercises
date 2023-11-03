package spring.repository;

import org.springframework.stereotype.Repository;
import spring.domain.Event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class EventRepository {
    private List<Event> events = new ArrayList<>(Arrays.asList(
            new Event("Jazz Concert", "Berlin"),
            new Event("Lumina Park", "Prague"),
            new Event("Violin Concert", "London")
    ));

    public List<Event> findAll() {
        return events;
    }

    public Event save(Event newEvent) {
        events.add(newEvent);
        return newEvent;
    }

    public Event findById(Integer id) {
        for (Event e : events) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    public void delete(Event event) {
        events.remove(event);
    }
}
