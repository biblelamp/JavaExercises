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

    public Event save(Event event) {
        if (event.getId() == null) {
            // add new Event
            Event newEvent = new Event(event.getName(), event.getCity());
            events.add(newEvent);
            return newEvent;
        } else {
            // update Event by id
            Event updEvent = findById(event.getId());
            if (updEvent != null) {
                updEvent.setName(event.getName());
                updEvent.setCity(event.getCity());
                return updEvent;
            }
            return null;
        }
    }

    public Event findById(int id) {
        for (Event event : events) {
            if (event.getId() == id) {
                return event;
            }
        }
        return null;
    }

    public Event remove(int id) {
        Event removeEvent = findById(id);
        events.remove(removeEvent);
        return removeEvent;
    }
}
