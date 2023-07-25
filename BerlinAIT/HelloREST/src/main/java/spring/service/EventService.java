package spring.service;

import org.springframework.stereotype.Service;
import spring.domain.Event;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {
    static final List<Event> events = new ArrayList<Event>(){{
        add(new Event("Opera", "London"));
        add(new Event("Violin concert", "Prague"));
        add(new Event("Jazz concert", "Berlin"));
    }};

    public List<Event> findAll() {
        return events;
    }

    public Event get(int id) {
        for (Event event : events) {
            if (event.getId() == id) {
                return event;
            }
        }
        return null;
    }

    public Event add(Event event) {
        Event newEvent = new Event(event.getName(), event.getCity());
        events.add(newEvent);
        return newEvent;
    }

    public Event update(int id, Event event) {
        Event updEvent = get(id);
        if (updEvent != null) {
            updEvent.setName(event.getName());
            updEvent.setCity(event.getCity());
        }
        return updEvent;
    }

    public Event delete(int id) {
        Event event = get(id);
        if (event != null) {
            events.remove(event);
        }
        return event;
    }
}
