package spring.repository;

import org.springframework.stereotype.Repository;
import spring.domain.Event;

import java.util.Arrays;
import java.util.List;

@Repository
public class EventRepository {
    static List<Event> events = Arrays.asList(
        new Event("Opera", "London"),
        new Event("Violin concert", "Prague"),
        new Event("Jazz concert", "Berlin"),
        new Event("Art exhibition", "London")
    );

    public List<Event> findAll() {
        return events;
    }

    public void save(Event event) {
        events.add(event);
    }
}
