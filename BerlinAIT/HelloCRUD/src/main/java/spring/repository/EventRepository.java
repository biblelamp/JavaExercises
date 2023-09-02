package spring.repository;

import org.springframework.stereotype.Repository;
import spring.domain.Event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class EventRepository {
    static List<Event> events = new ArrayList<>(Arrays.asList(
        new Event("Opera", "London"),
        new Event("Violin concert", "Prague"),
        new Event("Jazz concert", "Berlin"),
        new Event("Art exhibition", "London"),
        new Event("Royal Variety Show", "Paris")
    ));

    public List<Event> findAll() {
        return events;
    }

    public void save(Event event) {
        events.add(event);
    }

    public void save(int id, Event event) {
        Event updEvent = events.get(id);
        updEvent.setName(event.getName());
        updEvent.setCity(event.getCity());
    }

    public Event findById(int id) {
        return events.get(id);
    }

    public void remove(int id) {
        events.remove(id);
    }
}
